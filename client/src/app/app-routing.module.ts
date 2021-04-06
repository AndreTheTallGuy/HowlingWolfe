import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './components/cart/cart.component';
import { ContactComponent } from './components/contact/contact.component';
import { GuidedComponent } from './components/guided/guided.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LessonsComponent } from './components/lessons/lessons.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { RentComponent } from './components/rent/rent.component';
import { RentalComponent } from './components/rental/rental.component';

const routes: Routes = [
  { path: '', component: HomepageComponent},
  { path: 'rentals', component: RentalComponent },
  { path: 'lessons', component: LessonsComponent },
  { path: 'guided', component: GuidedComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'rentme', component: RentComponent },
  { path: 'cart', component: CartComponent },
  { path: '**', component: NotFoundComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }