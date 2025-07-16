const baseUrl = process.env.BASE_URL || "http://localhost:5106";
export const pages = {
  home: `${baseUrl}/`,
  login: `${baseUrl}/Identity/Account/Login`,
  basket: `${baseUrl}/Basket`,
  checkout: `${baseUrl}/Checkout`,
};
