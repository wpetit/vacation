// Define the http error interceptor
app.factory("httpErrorInterceptor", ["$q", "toaster", "$translate", function ($q, toaster, $translate) {
    var myInterceptor = {
        "responseError": function (rejection) {
        	if("403" == rejection.status) {
        		$translate(rejection.data).then(function (translation) {
        			toaster.pop('error',
							translation);
        		});
        	}
            return $q.reject(rejection);
        }
    };
    return myInterceptor;
}]);

// Configure the app with the http error interceptor
app.config(["$httpProvider", function ($httpProvider) {
    $httpProvider.interceptors.push("httpErrorInterceptor");
}]);