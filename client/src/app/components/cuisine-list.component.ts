import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-cuisine-list',
  templateUrl: './cuisine-list.component.html',
  styleUrls: ['./cuisine-list.component.css']
})
export class CuisineListComponent implements OnInit{
	// TODO Task 2
	// For View 1

  cuisines: Restaurant[] = []

  constructor(private restaurantsvc: RestaurantService){}
  ngOnInit(): void {
    this.restaurantsvc.getCuisineList()
    .then(result => {
      this.cuisines = result
      console.info('>>>> result', result)
    })
    .catch(error => {
      console.error('>>>>error:', error)
    })
  }

}
