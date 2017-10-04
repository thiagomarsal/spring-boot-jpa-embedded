var app = angular.module("myApp", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "productList.html"
        })
        .when("/product", {
            templateUrl: "productList.html"
        })
        .when("/productAdd", {
            templateUrl: "productAdd.html"
        })
        .when("/imageAdd", {
            templateUrl: "imageAdd.html"
        })
        .when("/image", {
            templateUrl: "imageList.html"
        });
});

app.controller('productController', function ($location, $scope, $http, $filter) {
    $scope.images = [];
    $scope.products = [];
    $scope.newProduct = null;
    $scope.selectedProduct = null;
    $scope.selectedImage = null;
    $scope.saveProduct = saveProduct;
    $scope.deleteProduct = deleteProduct;
    $scope.initProductAdd = initProductAdd;
    $scope.listProducts = listProducts;
    $scope.listImages = listImages;

    this.$onInit = function () {
        listProducts();
        listImages();
    }

    function initProductAdd() {
        $scope.newProduct = null;
        $location.path('/productAdd');
    }

    function saveProduct() {
        var product = $filter('filter')($scope.products, {'id':$scope.selectedProduct})[0];
        var images = [];

        if ($scope.selectedImage != null) {
            $scope.selectedImage.forEach(function (id) {
                var image = $filter('filter')($scope.images, {'id': id})[0];
                images.push(image);
            });
        }

        $scope.newProduct.product = product;
        $scope.newProduct.images = images;

        $http.post("http://localhost:8080/product/save", $scope.newProduct)
            .then(function (response) {
                $scope.products().push(response.data);
            });

        $location.path('/product');
    }

    function deleteProduct(product) {
        $http.get("http://localhost:8080/product/delete/" + product.id)
            .then(function (response) {
                var index = $scope.products.indexOf(product);
                $scope.products.splice(index, 1);
            });
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
    $scope.editImage = editImage;
    $scope.deleteImage = deleteImage;
    $scope.listImages = listImages;
    $scope.initImageAdd = initImageAdd;

    this.$onInit = function () {
        listImages();
    }

    function initImageAdd() {
        $scope.newImage = null;
        $location.path('/imageAdd');
    }

    function saveImage() {
        $http.post("http://localhost:8080/image/save", $scope.newImage)
            .then(function (response) {
                $scope.images.push(response.data);
            });

        $location.path('/image');
    }

    function editImage(image) {
        $scope.newImage = image;
        $location.path('/imageAdd');
    }

    function deleteImage(image) {
        $http.get("http://localhost:8080/image/delete/" + image.id)
            .then(function (response) {
                var index = $scope.images.indexOf(image);
                $scope.images.splice(index, 1);
            });
    }

    function listImages() {
        $http.get("http://localhost:8080/image/list")
        .then(function (response) {
            $scope.images = response.data;
        });
    }

});