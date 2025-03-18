import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-countries-list',
  standalone: true,
  imports: [RouterModule, CommonModule],
  template: `
    <h1>Countries</h1>
    <ul>
      <li *ngFor="let country of countries">
        {{ country.name }}
        <button (click)="removeCountry(country.id)">Remove</button>
        <button [routerLink]="['/edit-country', country.id]">Edit</button>
        <button [routerLink]="['/country-details', country.id]">Details</button>
      </li>
    </ul>
    <button routerLink="/add-country">Add Country</button>
  `,
})
export class CountriesListComponent {
  countries: any[] = [];

  constructor(private http: HttpClient) {
    this.fetchCountries();
  }

  fetchCountries() {
    this.http.get<any[]>('http://localhost:8080/api/countries').subscribe((data) => {
      this.countries = data;
    });
  }

  removeCountry(id: string) {
    this.http.delete(`http://localhost:8080/api/countries/${id}`).subscribe(() => this.fetchCountries());
  }
}
