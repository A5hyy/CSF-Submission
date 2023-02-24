import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Restaurant } from '../models';
import { RestaurantService } from '../restaurant-service';

@Component({
  selector: 'app-restaurant-cuisine',
  templateUrl: './restaurant-cuisine.component.html',
  styleUrls: ['./restaurant-cuisine.component.css']
})
export class RestaurantCuisineComponent implements OnInit{
	// TODO Task 3
	// For View 2
  params$!: Subscription
  restaurant: Restaurant[] = []

  constructor(private restaurantSvc: RestaurantService,
    private activatedRoute: ActivatedRoute) {}
  
ngOnInit(): void {
    this.params$ = this.activatedRoute.params.subscribe(
      (params) => {
        const cuisine = params['cuisine']
        this.restaurantSvc.getRestaurantsByCuisine(cuisine)
        .then(restaurantResult => {
          this.restaurant = restaurantResult
          console.info('>>>> restaurants', this.restaurant)
        })
        .catch(error => {
          console.error('>>>> error', error)
        })
      }
    )
}


}
