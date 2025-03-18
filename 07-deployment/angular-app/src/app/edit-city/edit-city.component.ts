import { Component } from '@angular/core';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-edit-city',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule],
  template: `
    <h1>Edit City</h1>
    <form (ngSubmit)="updateCity()">
      <label>
        Name:
        <input [(ngModel)]="name" name="name" required />
      </label>
      <br />
      <label>
        Population:
        <input [(ngModel)]="population" name="population" type="number" required />
      </label>
      <br />
      <button type="submit">Update</button>
    </form>
    <button [routerLink]="'/country-details/' + countryId">Back</button>
  `,
})
export class EditCityComponent {
  name = '';
  population: number | undefined;
  countryId = '';
  cityId = '';

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
    this.countryId = this.route.snapshot.params['id'];
    this.cityId = this.route.snapshot.params['cityId'];
    this.fetchCity();
  }

  fetchCity() {
    this.http.get<any>(`http://gateway:8080/api/cities/${this.cityId}`).subscribe((data) => {
      this.name = data.name;
      this.population = data.population;
    });
  }

  updateCity() {
    this.http
      .put(`http://gateway:8080/api/cities/${this.cityId}`, { name: this.name, population: this.population })
      .subscribe(() => {
        this.router.navigate([`/country-details/${this.countryId}`]);
      });
  }
}
