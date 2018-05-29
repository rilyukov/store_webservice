# store_webservice
This is simple REST API which is simulate working of web store.
You are able to:
 1. Add new user ({"email": "My.mail.com", "password": "secret"})  endpoint: /user/sign_up
 
 2. Sign in ({"email": "My.mail.com", "password": "secret"})       endpoint: /user/sign_in
  if you signed in successfully then you will get sessionId.
  It is requires to do next operations in store, 
  you should put this session id in header "sessionId" before performing other actions with store.
  Session time is 15 min. If user is not active within this time period - session will expire.
  
 3. You are able to see all products in store                     endpoint: /product
 
 4. You can add product in cart ({"id": 1, "quantity": 3})        endpoint: /cart/add
    API will check is enough products in store before adding. 
    
 5. You are able to edit cart item ({"id": 1, "quantity": 3})     endpoint: /cart/edit
 
 6. You are able to remove item from cart ({"id": 1})             endpoint: /cart/remove
 
 7. You can what is in the cart.                                  endpoint: /cart
    You will see all items in cart, also total will be calculated. 
    
 8. Finally you can checkout.                                     endpoint: /cart/checkout
    Api will check is enough products in store, then it will respond with 
    success ore error. If checkout was successfull it will update products quantity in store.
    
    
 Application use in memory DB.
 You are able to use web console on uri:http://localhost:8080/h2-console when application started. 
 By default we have 4 products.
 You can add additional by apdatind data.sql file in scr/main/resources
