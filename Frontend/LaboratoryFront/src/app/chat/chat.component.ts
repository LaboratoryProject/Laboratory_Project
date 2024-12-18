import { Component, OnInit, OnDestroy, Inject, PLATFORM_ID } from '@angular/core';
import { CommonModule, isPlatformBrowser } from '@angular/common';
import { WebSocketSubject } from 'rxjs/webSocket';
import { FormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-chat',
  imports:[FormsModule,CommonModule,HttpClientModule,],
  standalone: true,
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit, OnDestroy {
  private socket$: WebSocketSubject<any> | null = null;
  public messages: { sender: string; content: string }[] = [];
  public userMessage: string = '';
  private isBrowser: boolean;

  constructor(@Inject(PLATFORM_ID) private platformId: object,   private httpClient: HttpClient,) {
    // Detect if running in a browser
    this.isBrowser = isPlatformBrowser(this.platformId);

    if (this.isBrowser && 'WebSocket' in window) {
      this.socket$ = new WebSocketSubject('ws://localhost:8070/chat-websocket');

    } else {
      console.error('WebSocket is not available: not running in a browser.');
    }
  }

  ngOnInit(): void {
    if (this.isBrowser && this.socket$) {
      this.socket$.subscribe(
        (message: any) => {
          this.messages.push({ sender: 'AI', content: message.content });
        },
        (error) => console.error('WebSocket error:', error),
        () => console.warn('WebSocket connection closed')
      );
    }
  }

  sendMessage(): void {
    if (this.userMessage.trim()) {
      const payload = { userMessage: this.userMessage }; // Matches the backend DTO
  
      this.httpClient.post('http://localhost:8070/api/chat/ask', payload, { responseType: 'text' })
        .subscribe(
          (response: string) => {
            this.messages.push({ sender: 'AI', content: response });
          },
          (error) => {
            console.error('Error sending message', error);
          }
        );
  
      this.messages.push({ sender: 'You', content: this.userMessage });
      this.userMessage = '';
    }
  }
  

  ngOnDestroy(): void {
    if (this.isBrowser && this.socket$) {
      this.socket$.complete();
    }
  }
}
