import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {LocalStorage} from "ng2-localstorage/dist";

@Injectable()
export class OauthService {
  private url: String = "http://localhost:8080";
  @LocalStorage() private username: String;
  @LocalStorage() private password: String;
  @LocalStorage() private connected: boolean;
  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    console.log("hello");
    this.initHeader();
  }

  private initHeader(): void {
    this.headers = new HttpHeaders()
      .set('Authorization', 'Basic ' + btoa(this.username + ":" + this.password));
  }

  public login(username: String, password: String) {
    this.username = username;
    this.password = password;
    this.connected = true;
    this.initHeader();
  }

  public logout() {
    this.username = "";
    this.password = "";
    this.connected = false;
  }

  public getUsername(): String {
    return this.username;
  }

  public isConnected(): boolean {
    return this.connected;
  }

  public get<T>(path: String): Observable<T> {
    return this.http.get<T>(this.url + "" + path, {
      headers: this.headers
    });
  }

  public post(path: String, body: any): Observable<any> {
    return this.http.post(this.url + "" + path, body, {
      headers: this.headers
    });
  }

  public put(path: String, body: any): Observable<any> {
    return this.http.put(this.url + "" + path, body, {
      headers: this.headers
    });
  }

  public remove(path: String): Observable<any> {
    return this.http.delete(this.url + "" + path, {
      headers: this.headers
    });
  }
}
