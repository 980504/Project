package project.chatbotApp.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class DataLoader {

    @Value("${chatbotApp.pdf.directory:src/main/resources/pdfs}")
    private String pdfDirectoryPath;

    private final JdbcClient jdbcClient;
    private final VectorStore vectorStore;

    public DataLoader(JdbcClient jdbcClient, VectorStore vectorStore){
        this.jdbcClient = jdbcClient;
        this.vectorStore = vectorStore;
    }

    @PostConstruct
    public void initStore() {
        Integer count = jdbcClient.sql("SELECT COUNT(*) FROM vector_store")
                .query(Integer.class).single();

        if (count == 0) {
            try {
                File folder = new File(pdfDirectoryPath);
                File[] pdfs = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".pdf"));
                if (pdfs == null) return;

                for (File file : pdfs) {
                    PagePdfDocumentReader reader = new PagePdfDocumentReader(new FileSystemResource(file));
                    List<Document> documents = reader.get();
                    TextSplitter splitter = new TokenTextSplitter();
                    List<Document> chunks = splitter.split(documents);
                    vectorStore.add(chunks);
                }
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de l'indexation des fichiers PDF.");
            }
        }
    }
}
