/** Defining the endpoint **/
var endpoint = "http://localhost:3000/";

/** Utility function to send data **/
function post(route, data, $http, callback) {
    $http({
            method: 'POST',
            url: endpoint + route,
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            transformRequest: function(obj) {
                var str = [];
                for (var p in obj)
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                return str.join("&");
            },
            data: data
        }).success(function(data, status, headers, config) {
            callback(data);
        })
        .error(function(data, status, header, config) {
            console.log("Error:" + data);
        });
}

/** Utility function to get data **/
function get(route, params, $http, callback) {
    var config = {
        params: params
    };

    $http.get(endpoint + route, config)
        .success(function(data, status, headers, config) {
            callback(data);
        })
        .error(function(data, status, header, config) {
            console.log("Error:" + data);
        });
}

/** Utilitis for form verification **/
function checkEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}

function checkEmpty(str) {
    return str.length > 0;
}

function checkPassword(pwd, cpwd) {
    return pwd.length > 4 && pwd == cpwd;
}

/** The app starts here **/
var app = angular.module('PartyList', [])
    .controller('LoginRegisterCtrl', function($scope, $http) {
        /** Initializing Login/ Register Objects **/
        $scope.LoginObject = {};
        $scope.RegisterObject = {};

        /** Login function **/
        $scope.login = function() {
            uname = $scope.LoginObject.username;
            pwd = $scope.LoginObject.password;
            if (!checkEmpty(uname) || !checkEmpty(pwd))
                alert('Incorrect login information.');
            else {
                params = { username: uname, password: pwd };
                post('accounts/login', params, $http, function(data) {
                    if (data.success)
                        window.location.replace('./dashboard');
                    else
                        alert('Login unsuccessful, please enter valid credentials!');
                });
            }
        }

        /** Register function **/
        $scope.register = function() {
            uname = $scope.RegisterObject.username;
            pwd = $scope.RegisterObject.password;
            cpwd = $scope.RegisterObject.confirm_password;
            uemail = $scope.RegisterObject.email;

            if (!checkEmail(uemail) || !checkEmpty(uname) ||
                !checkPassword(pwd, cpwd))
                alert('Incorrect user information.');
            else {
                params = { username: uname, password: pwd, emai: uemail };
                post('accounts/create', params, $http, function(data) {
                    if (data.success)
                        window.location.replace('./dashboard');
                    else
                        alert('Registration unsuccessful, please enter valid credentials!');
                });
            }
        }
    })
    .directive('ngEnter', function() {
        return function(scope, element, attrs) {
            element.bind("keydown keypress", function(event) {
                if (event.which === 13) {
                    scope.$apply(function() {
                        scope.$eval(attrs.ngEnter);
                    });

                    event.preventDefault();
                }
            });
        };
    });
