import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import { UserListViewComponent } from './user-list-view/user-list-view.component';
import {DataService} from "./data-service.service";
import { TopicViewComponent } from './topic-view/topic-view.component';


@NgModule({
  imports: [
    BrowserModule, HttpClientModule, FormsModule
  ],
  declarations: [
    AppComponent,
    UserListViewComponent,
    TopicViewComponent,

  ],
  providers: [DataService
   ],
  bootstrap: [AppComponent]
})
export class AppModule { }
