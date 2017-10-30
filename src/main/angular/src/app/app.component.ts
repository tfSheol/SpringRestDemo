import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

class Account {
  email: String;
  username: String;
  password: String;
  edit: boolean = false;
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public title = 'app';
  public users: Account[] = [];
  public error: String = "";

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.getAllUser();
  }

  getAllUser(): void {
    this.error = "";
    const headers = new HttpHeaders()
      .set('Authorization', 'Basic ' + btoa("test:test"));
    this.http.get<Account[]>("http://localhost:8080/account", {
      headers: headers
    }).subscribe(bla => {
      this.users = bla;
    })
  }

  addUser(user: Account): void {
    this.http.post("http://localhost:8080/account", user).subscribe(bla => {
      this.onResult(bla);
    })
  }

  editUser(current: Account, show: boolean): void {
    current.edit = show;
  }

  putUser(currentUser: String, user: Account): void {
    this.http.put("http://localhost:8080/account/" + currentUser, user).subscribe(bla => {
      this.onResult(bla);
    })
  }

  deleteUser(currentUser: String): void {
    this.http.delete("http://localhost:8080/account/" + currentUser).subscribe(bla => {
      this.onResult(bla);
    })
  }

  deleteAllUser(): void {
    this.http.delete("http://localhost:8080/account").subscribe(bla => {
      this.onResult(bla);
    })
  }

  onResult(bla: any): void {
    if (bla["error"] != undefined) {
      console.log(bla["error"]);
      this.error = bla["error"];
    } else {
      this.getAllUser();
    }
  }
}
