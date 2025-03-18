import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-country',
  standalone: true,
  imports: [FormsModule],
  template: `
    <h1>Add Country</h1>
    <form (ngSubmit)="addCountry()">
      <label>
        Name:
        <input [(ngModel)]="name" name="name" required />
      </label>
      <br />
      <label>
        Population:
        <input [(ngModel)]="population" name="population" required type="number" />
      </label>
      <br />
      <button type="submit">Submit</button>
    </form>
    <button (click)="onBackClick()">Back</button>
  `,
})
export class AddCountryComponent {
  name = '';
  population: number | undefined;  // Declare population as a number type

  constructor(private http: HttpClient, private router: Router) {}

  addCountry() {
    // Send the name and population data to the backend
    const countryData = { name: this.name, population: this.population };
    this.http.post('http://localhost:8080/api/countries', countryData).subscribe(() => {
      this.router.navigate(['/']);
    });
  }

  onBackClick() {
    this.router.navigate(['/']);
  }
}
