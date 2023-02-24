import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-restaurant-details',
  templateUrl: './restaurant-details.component.html',
  styleUrls: ['./restaurant-details.component.css']
})
export class RestaurantDetailsComponent {
	
	// TODO Task 4 and Task 5
	// For View 3
  form!: FormGroup

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    // Build the form
    this.form = this.createForm()
  }

  onSubmit(){

  }

  private createForm(): FormGroup {
    return this.fb.group({
      name: this.fb.control<string>('', [ Validators.required ]),
      rating: this.fb.control<number>(0, [ Validators.required ]),

    })
  }

}
