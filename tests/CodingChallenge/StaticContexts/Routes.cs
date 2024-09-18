namespace CodingChallenge.StaticContexts
{
    public static class Routes
    {
        public static class PageRoutes
        {
            public const string LoginPage = "Identity/Account/Login";
            public const string BasketPage = "Basket";
            public const string CheckoutPage = "Basket/Checkout";
            public const string SuccessPage = "Basket/Success";
        }

        public static class ApiRoutes 
        {
            public const string Authentication = "api/authenticate";
            public const string CatalogBrands = "api/catalog-brands";
            public const string AuthRoute = "api/authenticate";
        }

    }
}
