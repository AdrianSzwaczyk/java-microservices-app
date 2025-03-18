import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-edit-country',
  standalone: true,
  imports: [FormsModule],
  template: `
    <h1>Edit Country</h1>
    <form (ngSubmit)="updateCountry()">
      <label>
        Name:
        <input [(ngModel)]="name" name="name" required />
      </label>
      <br/>
      <label>
        Population:
        <input [(ngModel)]="population" name="population" type="number" required />
      </label>
      <br/>
      <button type="submit">Update</button>
    </form>
    <button (click)="onBackClick()">Back</button>
  `,
})
export class EditCountryComponent {
  name = '';
  population = 0; // To store the population
  id = '';

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
    this.id = this.route.snapshot.params['id'];
    this.fetchCountry();
  }

  fetchCountry() {
    this.http.get<any>(`http://gateway:8080/api/countries/${this.id}`).subscribe((data) => {
      this.name = data.name;
      this.population = data.population; // Assuming population is returned from the backend
    });
  }

  updateCountry() {
    // Send both name and population when updating the country
    this.http.put(`http://gateway:8080/api/countries/${this.id}`, { 
      name: this.name,
      population: this.population
    }).subscribe(() => {
      this.router.navigate(['/']);
    });
  }

  onBackClick() {
    this.router.navigate(['/']);
  }
}
