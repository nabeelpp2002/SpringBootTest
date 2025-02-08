# Currency Converter Application

This is a simple Spring Boot application that uses the Exchange Rate API to provide real-time currency conversion functionality. The application allows users to convert an amount from one currency to another.

---

## **Steps to Run the Application**

### **1. Prerequisites**
Ensure the following are installed on your system:
- Java 17 or higher
- Maven 3.x or higher
- An IDE (optional) like Eclipse, IntelliJ IDEA, or Visual Studio Code

### **2. Clone the Repository**
```bash
# Clone the repository to your local system
git clone <repository-url>
cd <repository-folder>
```

### **3. Configure the Application**
The application uses the Exchange Rate API. Ensure you have an API key.
1. Open the `application.properties` file located in `src/main/resources`.
2. Replace the placeholder API key with your actual API key:
   ```properties
   exchange.rate.api.key=c6fa4e2576417c21b5a99c70
   ```

### **4. Build and Run the Application**
To build and run the application, use the following Maven commands:
```bash
# Compile the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on port **8081** by default.

---

## **Using the Application**

### **Access the Application in a Browser**
To perform a currency conversion, open your browser and use the following endpoint:
```
http://localhost:8080/api/convert?base=USD&target=INR&amount=1
```

### **Parameters**
- `base`: The currency code for the base currency (e.g., USD).
- `target`: The currency code for the target currency (e.g., INR).
- `amount`: The amount to be converted.

### **Example**
To convert 1 USD to INR:
```
http://localhost:8080/api/convert?base=USD&target=INR&amount=1
```

### **Expected Output**
```json
{
  "from": "USD",
  "to": "INR",
  "amount": 1,
  "convertedAmount": 83.26
}
```
(Note: The converted value may vary depending on real-time exchange rates.)

---

## **Endpoints**

### **1. Convert Currency**
**Method**: `GET`
**URL**: `/api/convert`
**Description**: Converts an amount from one currency to another.

**Example Request**:
```
http://localhost:8080/api/convert?base=USD&target=EUR&amount=100
```

**Response**:
```json
{
  "from": "USD",
  "to": "EUR",
  "amount": 100,
  "convertedAmount": 85.0
}
```

---

## **Troubleshooting**

### **Common Issues**
1. **Port 8080 is already in use**:
   - Edit `src/main/resources/application.properties` and update the server port:
     ```properties
     server.port=8081
     ```

2. **API key issues**:
   - Ensure your API key is valid and correctly set in `application.properties`.

3. **Application fails to start**:
   - Ensure all dependencies are installed using:
     ```bash
     mvn clean install
     ```

---

## **Contact**
For any questions or issues, feel free to reach out to the developer.

Enjoy using the Currency Converter Application!

