import { Component } from '@angular/core';
import { ActivatedRoute, RouterModule, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-city-details',
  standalone: true,
  imports: [RouterModule, CommonModule],
  template: `
    <h1>City Details</h1>
    <p><strong>Name:</strong> {{ city?.name }}</p>
    <p><strong>Country:</strong> {{ country?.name }}</p>
    <p><strong>Population:</strong> {{ city?.population }}</p> <!-- Display population here -->
    <button (click)="onBackClick()">Back</button>
  `,
})
export class CityDetailsComponent {
  city: any;
  country: any;
  countryId = '';
  cityId = '';

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
    this.countryId = this.route.snapshot.params['id'];
    this.cityId = this.route.snapshot.params['cityId'];
    this.fetchCity();
    this.fetchCountry();
  }

  fetchCity() {
    this.http
      .get<any>(`http://gateway:8080/api/cities/${this.cityId}`)
      .subscribe((data) => {
        this.city = data;
      });
  }

  fetchCountry() {
    this.http
      .get<any>(`http://gateway:8080/api/countries/${this.countryId}`)
      .subscribe((data) => {
        this.country = data;
      });
  }

  onBackClick() {
    this.router.navigate([`/country-details/${this.countryId}`]);
  }
}
