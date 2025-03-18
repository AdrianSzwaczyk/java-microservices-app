import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-add-city',
  standalone: true,
  imports: [FormsModule],
  template: `
    <h1>Add City</h1>
    <form (ngSubmit)="addCity()">
      <label>
        Name:
        <input [(ngModel)]="name" name="name" required />
      </label>
      <br/>
      <label>
        Population:
        <input [(ngModel)]="population" name="population" type="number" required />
      </label>

      <button type="submit">Submit</button>
    </form>

    <button (click)="onBackClick()">Back</button>
  `,
})
export class AddCityComponent {
  name = '';
  population = 0;
  countryId = '';

  constructor(private route: ActivatedRoute, private http: HttpClient, private router: Router) {
    this.countryId = this.route.snapshot.params['id'];
  }

  addCity() {
    this.http
      .post(`http://localhost:8080/api/cities/${this.countryId}`, { name: this.name, population: this.population })
      .subscribe(() => {
        this.router.navigate([`/country-details/${this.countryId}`]);
      });
  }

  onBackClick() {
    this.router.navigate([`/country-details/${this.countryId}`]);
  }
}
