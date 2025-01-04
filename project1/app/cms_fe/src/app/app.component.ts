import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent implements OnInit {
  date!: Date;
  counter = 0;

  ngOnInit(): void {
    this.date = new Date();
  }

  increaseCounter() {
    this.updateCounter(+1);
  }

  decreaseCounter() {
    this.updateCounter(-1);
  }

  updateCounter(numberToAdd: number) {
    this.counter += numberToAdd;
  }

  title = 'CMS';
  user = 'Aizen';
}
