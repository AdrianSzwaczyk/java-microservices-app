import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-country-details',
  standalone: true,
  imports: [RouterModule, CommonModule],
  template: `
    <h1>Country Details</h1>
    <p><strong>Name:</strong> {{ country?.name }}</p>
    <p><strong>Population:</strong> {{ country?.population }}</p>
    <p><strong>Cities:</strong></p>
    <ul *ngIf="cities.length > 0; else noCities">
      <li *ngFor="let city of cities">
        {{ city.name }}
        <button (click)="removeCity(city.id)">Remove</button>
        <button [routerLink]="['edit-city', city.id]">Edit</button>
        <button [routerLink]="['city-details', city.id]">Details</button>
      </li>
    </ul>

    <ng-template #noCities>
      <p>No cities available in this country.</p>
    </ng-template>

    <button [routerLink]="['add-city']">Add City</button>
    <button routerLink="/">Back</button>
  `,
})
export class CountryDetailsComponent {
  country: any;
  cities: any[] = [];
  id = '';

  constructor(private route: ActivatedRoute, private http: HttpClient) {
    this.id = this.route.snapshot.params['id'];
    this.fetchDetails();
  }

  fetchDetails() {
    this.http.get<any>(`http://localhost:8080/api/countries/${this.id}`).subscribe((data) => {
      this.country = data;
    });
    
    this.http.get<any[]>(`http://localhost:8080/api/cities/country/${this.id}`).subscribe((data) => {
      this.cities = data;
    });
  }

  removeCity(cityId: string) {
    this.http.delete(`http://localhost:8080/api/cities/${cityId}`).subscribe(() => {
      this.cities = this.cities.filter((city) => city.id !== cityId);
    });
  }
}
