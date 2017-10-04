var app = angular.module("myApp", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "product.html"
        })
        .when("/product", {
            templateUrl: "product.html"
        })
        .when("/productAdd", {
            templateUrl: "productAdd.html"
        })
        .when("/imageAdd", {
            templateUrl: "imageAdd.html"
        })
        .when("/image", {
            templateUrl: "image.html"
        });
});

app.controller('productController', function ($location, $scope, $http) {
    $scope.images = [];
    $scope.products = [];
    $scope.newProduct = null;
    $scope.saveProduct = saveProduct;
    $scope.initProductAdd = initProductAdd;
    $scope.listProducts = listProducts;
    $scope.listImages = listImages;

    function initProductAdd() {
        $scope.newProduct = null;
        $location.path('/productAdd');
    }

    function saveProduct() {
        $scope.products.push($scope.newProduct);
        $scope.newProduct = null;
        listProducts();
        $location.path('/product');
    }

    function listProducts() {
        $http.get("http://localhost:8080/product/list")
        .then(function (response) {
            $scope.products = response.data;
        });
    }

    function listImages() {
        $http.get("http://localhost:8080/image/list")
        .then(function (response) {
            $scope.images = response.data;
        });
    }

});

app.controller('imageController', function ($location, $scope, $http) {
    $scope.images = [];
    $scope.newImage = null;
    $scope.saveImage = saveImage;
    $scope.listImages = listImages;
    $scope.initImageAdd = initImageAdd;

    function initImageAdd() {
        $scope.newImage = null;
        $location.path('/imageAdd');
    }

    function saveImage() {
        $scope.images.push($scope.newImage);
        $scope.newImage = null;
        listImages();
        $location.path('/image');
    }

    function listImages() {
        $http.get("http://localhost:8080/image/list")
        .then(function (response) {
            $scope.images = response.data;
        });
    }

});