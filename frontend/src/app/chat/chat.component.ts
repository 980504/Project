import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule, HttpEventType } from '@angular/common/http';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule],
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  file: File | null = null;
  inputText: string = '';
  submittedText: string = '';
  message: string = '';
  uploading: boolean = false;

  constructor(private http: HttpClient) {}

  onFileSelected(event: any): void {
    this.file = event.target.files[0];
    console.log('Fichier sélectionné :', this.file);
  }

  onUpload(): void {
    if (!this.file) {
      this.message = 'Aucun fichier sélectionné';
      return;
    }

    const formData = new FormData();
    formData.append('file', this.file);
    this.uploading = true;

    this.http.post('http://localhost:8080/files/upload', formData, {
      responseType: 'text',
      reportProgress: true,
      observe: 'events'
    }).subscribe({
      next: (event) => {
        console.log('Événement:', event);
        if (event.type === HttpEventType.Response) {
          this.message = event.body || 'Fichier uploadé avec succès';
          this.uploading = false;
        }
      },
      error: (err) => {
        console.error('Erreur lors de l\'upload :', err);
        this.message = 'Erreur lors de l\'upload : ' + err.message;
        this.uploading = false;
      }
    });
  }

  onSubmitQuestion(): void {
    const url = `http://localhost:8080/chat/ask?question=${encodeURIComponent(this.inputText)}`;
    this.http.get(url, { responseType: 'text' }).subscribe({
      next: (response) => this.submittedText = response,
      error: () => this.submittedText = 'Erreur lors de la récupération de la réponse.'
    });
  }
}
