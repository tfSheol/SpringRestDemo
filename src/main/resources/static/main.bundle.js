webpackJsonp(["main"],{

/***/ "../../../../../src/$$_gendir lazy recursive":
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "../../../../../src/$$_gendir lazy recursive";

/***/ }),

/***/ "../../../../../src/app/app.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "h2 {\n  color: red;\n}\n", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/app.component.html":
/***/ (function(module, exports) {

module.exports = "<div style=\"text-align:center\">\n  <h1>\n    Welcome to {{title}}!\n  </h1>\n  <h2>{{this.error}}</h2>\n  <div *ngIf=\"oauth.isConnected()\">\n    <button (click)=\"deleteAllUser()\">delete all users</button>\n    <div *ngFor='let user of users'>\n      {{user.username}}<br/>\n      {{user.email}}\n      <div *ngIf=\"user.edit\">\n        <button (click)=\"editUser(user, false)\">close</button>\n        <form #data=\"ngForm\" (ngSubmit)=\"putUser(user.username, data.value)\">\n          <input type=\"text\" [ngModel]=\"user.username\" placeholder=\"username\" name=\"username\" ngModel>\n          <input type=\"password\" [ngModel]=\"user.password\" placeholder=\"password\" name=\"password\" ngModel>\n          <input type=\"text\" [ngModel]=\"user.email\" placeholder=\"email\" name=\"email\" ngModel>\n          <button type=\"submit\">Submit</button>\n        </form>\n      </div>\n      <button *ngIf=\"!user.edit\" (click)=\"editUser(user, true)\">edit</button>\n      <button *ngIf=\"!user.edit\" (click)=\"deleteUser(user.username)\">delete</button>\n    </div>\n    <br/>\n    <div>\n      <form #data=\"ngForm\" (ngSubmit)=\"addUser(data.value)\">\n        <input type=\"text\" placeholder=\"username\" name=\"username\" ngModel>\n        <input type=\"password\" placeholder=\"password\" name=\"password\" ngModel>\n        <input type=\"text\" placeholder=\"email\" name=\"email\" ngModel>\n        <button type=\"submit\">Submit</button>\n      </form>\n    </div>\n  </div>\n  <br/><br/>\n  <div>\n    <form *ngIf=\"!oauth.isConnected()\" #data_login=\"ngForm\" (ngSubmit)=\"login(data_login.value)\">\n      <input type=\"text\" placeholder=\"username\" name=\"username\" ngModel>\n      <input type=\"password\" placeholder=\"password\" name=\"password\" ngModel>\n      <button type=\"submit\">Login</button>\n    </form>\n    <button *ngIf=\"oauth.isConnected()\" (click)=\"logout()\">Logout : {{oauth.getUsername()}}</button>\n  </div>\n</div>\n\n"

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/@angular/common/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__oauth_service__ = __webpack_require__("../../../../../src/app/oauth.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var Account = (function () {
    function Account() {
        this.edit = false;
    }
    return Account;
}());
var AppComponent = (function () {
    function AppComponent(http, oauth) {
        this.http = http;
        this.oauth = oauth;
        this.title = 'app';
        this.users = [];
        this.error = "";
    }
    AppComponent.prototype.ngOnInit = function () {
        this.getAllUser();
    };
    /*
      let body = new URLSearchParams();
      body.set('username', username);
      body.set('password', password);
  
      this.http.post(this.loginUrl, body).map(...);
     */
    AppComponent.prototype.login = function (user) {
        var _this = this;
        this.oauth.login(user.username, user.password).subscribe(function (bla) {
            bla.connected = true;
            _this.oauth.setToken(bla);
            _this.getAllUser();
        }, function (error) { return _this.oauth.error(error); });
    };
    AppComponent.prototype.logout = function () {
        this.oauth.logout();
        this.users = [];
    };
    AppComponent.prototype.getAllUser = function () {
        var _this = this;
        this.error = "";
        this.oauth.get("/account").subscribe(function (bla) {
            _this.users = bla;
        }, function (error) { return _this.oauth.error(error); });
    };
    AppComponent.prototype.addUser = function (user) {
        var _this = this;
        this.oauth.post("/account", user).subscribe(function (bla) {
            _this.onResult(bla);
        }, function (error) { return _this.oauth.error(error); });
    };
    AppComponent.prototype.editUser = function (current, show) {
        current.edit = show;
    };
    AppComponent.prototype.putUser = function (currentUser, user) {
        var _this = this;
        this.oauth.put("/account/" + currentUser, user).subscribe(function (bla) {
            _this.onResult(bla);
        }, function (error) { return _this.oauth.error(error); });
    };
    AppComponent.prototype.deleteUser = function (currentUser) {
        var _this = this;
        this.oauth.remove("/account/" + currentUser).subscribe(function (bla) {
            _this.onResult(bla);
        }, function (error) { return _this.oauth.error(error); });
    };
    AppComponent.prototype.deleteAllUser = function () {
        var _this = this;
        this.oauth.remove("/account").subscribe(function (bla) {
            _this.onResult(bla);
        }, function (error) { return _this.oauth.error(error); });
    };
    AppComponent.prototype.onResult = function (bla) {
        if (bla["error"] != undefined) {
            console.log(bla["error"]);
            this.error = bla["error"];
        }
        else {
            this.getAllUser();
        }
    };
    return AppComponent;
}());
AppComponent = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Component"])({
        selector: 'app-root',
        template: __webpack_require__("../../../../../src/app/app.component.html"),
        styles: [__webpack_require__("../../../../../src/app/app.component.css")]
    }),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]) === "function" && _a || Object, typeof (_b = typeof __WEBPACK_IMPORTED_MODULE_2__oauth_service__["a" /* OauthService */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_2__oauth_service__["a" /* OauthService */]) === "function" && _b || Object])
], AppComponent);

var _a, _b;
//# sourceMappingURL=app.component.js.map

/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("../../../platform-browser/@angular/platform-browser.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__angular_common_http__ = __webpack_require__("../../../common/@angular/common/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__angular_forms__ = __webpack_require__("../../../forms/@angular/forms.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__oauth_service__ = __webpack_require__("../../../../../src/app/oauth.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};






var AppModule = (function () {
    function AppModule() {
    }
    return AppModule;
}());
AppModule = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["NgModule"])({
        declarations: [
            __WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* AppComponent */]
        ],
        imports: [
            __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
            __WEBPACK_IMPORTED_MODULE_2__angular_common_http__["b" /* HttpClientModule */],
            __WEBPACK_IMPORTED_MODULE_3__angular_forms__["a" /* FormsModule */]
        ],
        providers: [__WEBPACK_IMPORTED_MODULE_5__oauth_service__["a" /* OauthService */]],
        bootstrap: [__WEBPACK_IMPORTED_MODULE_4__app_component__["a" /* AppComponent */]]
    })
], AppModule);

//# sourceMappingURL=app.module.js.map

/***/ }),

/***/ "../../../../../src/app/oauth.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return OauthService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/@angular/common/http.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ng2_localstorage_dist__ = __webpack_require__("../../../../ng2-localstorage/dist/index.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_ng2_localstorage_dist___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_2_ng2_localstorage_dist__);
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var Token = (function () {
    function Token() {
        this.token = "";
        this.tokenType = "";
        this.username = "";
        this.ttl = 0;
        this.connected = false;
    }
    return Token;
}());
var OauthService = (function () {
    function OauthService(http) {
        this.http = http;
        this.url = "http://localhost:8080/api";
        this.token = new Token();
        this.initHeader();
    }
    OauthService.prototype.initHeader = function () {
        this.headers = new __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["c" /* HttpHeaders */]()
            .set('Authorization', this.token.tokenType + ' ' + this.token.token);
    };
    OauthService.prototype.login = function (username, password) {
        return this.http.post(this.url + "/oauth/auth", null, {
            headers: new __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["c" /* HttpHeaders */]()
                .set('Authorization', 'Basic ' + btoa(username + ":" + password))
        });
    };
    OauthService.prototype.setToken = function (token) {
        this.token = token;
        this.initHeader();
    };
    OauthService.prototype.logout = function () {
        this.token = new Token();
    };
    OauthService.prototype.getUsername = function () {
        return this.token.username;
    };
    OauthService.prototype.isConnected = function () {
        return this.token.connected;
    };
    OauthService.prototype.get = function (path) {
        return this.http.get(this.url + "" + path, {
            headers: this.headers
        });
    };
    OauthService.prototype.post = function (path, body) {
        return this.http.post(this.url + "" + path, body, {
            headers: this.headers
        });
    };
    OauthService.prototype.put = function (path, body) {
        return this.http.put(this.url + "" + path, body, {
            headers: this.headers
        });
    };
    OauthService.prototype.remove = function (path) {
        return this.http.delete(this.url + "" + path, {
            headers: this.headers
        });
    };
    OauthService.prototype.error = function (error) {
        this.logout();
    };
    return OauthService;
}());
__decorate([
    Object(__WEBPACK_IMPORTED_MODULE_2_ng2_localstorage_dist__["LocalStorage"])(),
    __metadata("design:type", Token)
], OauthService.prototype, "token", void 0);
OauthService = __decorate([
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["Injectable"])(),
    __metadata("design:paramtypes", [typeof (_a = typeof __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */] !== "undefined" && __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]) === "function" && _a || Object])
], OauthService);

var _a;
//# sourceMappingURL=oauth.service.js.map

/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
// The file contents for the current environment will overwrite these during build.
var environment = {
    production: false
};
//# sourceMappingURL=environment.js.map

/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/@angular/core.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("../../../platform-browser-dynamic/@angular/platform-browser-dynamic.es5.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("../../../../../src/app/app.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["enableProdMode"])();
}
Object(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */])
    .catch(function (err) { return console.log(err); });
//# sourceMappingURL=main.js.map

/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map