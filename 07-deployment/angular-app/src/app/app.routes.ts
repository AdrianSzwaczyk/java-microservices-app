import { Routes } from '@angular/router';
import { CountriesListComponent } from './countries-list/countries-list.component';
import { AddCountryComponent } from './add-country/add-country.component';
import { EditCountryComponent } from './edit-country/edit-country.component';
import { CountryDetailsComponent } from './country-details/country-details.component';
import { AddCityComponent } from './add-city/add-city.component';
import { EditCityComponent } from './edit-city/edit-city.component';
import { CityDetailsComponent } from './city-details/city-details.component';

export const appRoutes: Routes = [
  { path: '', component: CountriesListComponent },
  { path: 'add-country', component: AddCountryComponent },
  { path: 'edit-country/:id', component: EditCountryComponent },
  { path: 'country-details/:id', component: CountryDetailsComponent },
  { path: 'country-details/:id/add-city', component: AddCityComponent },
  { path: 'country-details/:id/edit-city/:cityId', component: EditCityComponent },
  { path: 'country-details/:id/city-details/:cityId', component: CityDetailsComponent },
  { path: '**', redirectTo: '' },
];
