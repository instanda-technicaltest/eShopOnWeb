using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CodingChallenge.Utils
{
    public class Routes
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
