import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable  } from 'rxjs';
import { User } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class UserStateService {
  // BehaviorSubject to hold the user ID
  // BehaviorSubject to hold the user ID
  private userIdSubject = new BehaviorSubject<string | null>(null);
  private userSubject = new BehaviorSubject<User | null>(null);


  // Observable that can be subscribed to for getting the user ID
  userId$ = this.userIdSubject.asObservable();
 // Expose the observable stream of user data
   user$: Observable<User | null> = this.userSubject.asObservable();

  // Method to set the user ID
  setUserId(id: string | null) {
    this.userIdSubject.next(id);
  }

  // Method to get the current value of the user ID
  getUserId(): string | null {
    return this.userIdSubject.value;
  }

  // Method to update the user state
  setUser(user: User): void {
    this.userSubject.next(user);
  }

  // Method to clear the user state
  clearUser(): void {
    this.userSubject.next(null);
  }

  // Optional: Method to retrieve the current user value synchronously
  getUser(): User | null {
    return this.userSubject.getValue();
  }

}
