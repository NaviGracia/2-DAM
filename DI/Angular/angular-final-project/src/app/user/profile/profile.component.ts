import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  standalone: true,
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  imports: [CommonModule, ReactiveFormsModule],
})
export class ProfileComponent {
  profileForm: FormGroup;
  user: any = {};

  constructor(private fb: FormBuilder, private router: Router) {
    this.loadUserData();
    this.profileForm = this.fb.group({
      username: [this.user.username, [Validators.required, Validators.minLength(3)]],
      email: [this.user.email, [Validators.required, Validators.email]],
      password: ['', [Validators.minLength(6)]], // Opcional cambiar contraseña
    });
  }

  loadUserData() {
    const storedUser = JSON.parse(localStorage.getItem('user') || '{}');
    this.user = storedUser || {};
  }

  saveProfile() {
    if (this.profileForm.valid) {
      const updatedUser = {
        username: this.profileForm.value.username,
        email: this.profileForm.value.email,
        password: this.profileForm.value.password || this.user.password, // Mantener contraseña si no se cambia
      };

      localStorage.setItem('user', JSON.stringify(updatedUser));
      alert('Perfil actualizado correctamente');
      this.router.navigate(['/']);
    }
  }
}
