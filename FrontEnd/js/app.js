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


/** The app starts here **/
var app = angular.module('PartyList', [])
    .controller('LoginRegisterCtrl', function($scope, $http) {
        $scope.LoginObject = {}

        /** Login function **/
        $scope.login = function(){
            params = {username: LoginObject.username, password: LoginObject.password};
            post('login', params, $http, function(data){
                if(data.success)
                    window.location.replace('./dashboard');
                else
                    alert('Login unsuccessful, please enter valid credentials!');
            });
        }

        /** Register function **/
        $scope.register = function(){
            uname = RegisterObject.username;
            pwd = RegisterObject.password;
            cpwd = RegisterObject.confirm_password;
            name = RegisterObject.name;
            uemail = RegisterObject.email;

            if(!checkEmail(uemail) || !checkUsername(uname) || 
                !checkPassword(pwd, cpwd) || !checkName(name))
                alert('Incorrect user information.');
            params = {name: name, username: uname, password: pwd, emai: uemail};
             post('register', params, $http, function(data){
                if(data.success)
                    window.location.replace('./dashboard');
                else
                    alert('Registration unsuccessful, please enter valid credentials!');
            });
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
