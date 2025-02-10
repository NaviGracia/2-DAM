import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private router: Router) {}

  register(user: { username: string; email: string; password: string }): boolean {
    const existingUser = JSON.parse(localStorage.getItem('user') || 'null');
    
    if (existingUser && existingUser.email === user.email) {
      return false; // Usuario ya registrado
    }

    localStorage.setItem('user', JSON.stringify(user));
    return true;
  }

  login(email: string, password: string): boolean {
    const user = JSON.parse(localStorage.getItem('user') || 'null');

    if (user && user.email === email && user.password === password) {
      localStorage.setItem('isLoggedIn', 'true');
      return true;
    }

    return false; // Credenciales incorrectas
  }

  logout() {
    localStorage.removeItem('isLoggedIn');
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return localStorage.getItem('isLoggedIn') === 'true';
  }
}
