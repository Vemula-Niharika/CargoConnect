# 🚚 CargoConnect – Smart Logistics Management System

**CargoConnect** is a full-stack logistics management system built with **Java (Spring Boot)**, **Spring Data JPA**, **PostgreSQL**, **HTML**, **CSS**, and **JavaScript**.  
It streamlines logistics operations by enabling administrators to manage **drivers, trucks, carriers, addresses, and orders** — while users can place and track their cargo bookings efficiently.

## 🧾 Project Overview

Logistics companies often face challenges managing multiple resources such as drivers, vehicles, and deliveries.  
**CargoConnect** automates these tasks by providing an easy-to-use web interface and backend system to organize cargo operations, minimize manual errors, and improve efficiency.

Note: The project is internally named "abclogistics" and uses a database named "abclogistics". The repository is named "CargoConnect" for branding purposes.

## 🌟 Features

### 👨‍💼 Admin Panel
- Add, update, or delete **Drivers**, **Trucks**, **Carriers**, and **Addresses**
- Manage and track **Orders**
- Assign drivers and vehicles to cargo deliveries
- Update delivery and booking statuses
### 👤 User Panel
- Place new cargo orders
- Select pickup and delivery addresses
- View order confirmations and booking details
## 🛠️ Technologies Used

| Layer | Technology |
|-------|-------------|
| **Frontend** | HTML5, CSS3, JavaScript |
| **Backend** | Java, Spring Boot, Spring Data JPA |
| **Database** | PostgreSQL |
| **Build Tool** | Maven |
| **IDE** | Eclipse |
| **Language** | Java 17 |

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository
git clone https://github.com/Vemula-Niharika/CargoConnect.git

2️⃣ Open in Eclipse or IntelliJ IDEA
Go to File → Import → Existing Maven Project
Select the cloned project folder

3️⃣ Configure PostgreSQL Database
Create a new PostgreSQL database named abclogistics
Update your database credentials in the application.properties file:

4️⃣ Run the Application
Using Maven:
mvn clean install
mvn spring-boot:run
Or 
run the main class abclogisticsApplication.java from your IDE.

5️⃣Open Frontend in Browser
Open Chrome and type:
http://localhost:8080/index.html
The Admin and User pages will appear.
From here, you can manage carriers, trucks, drivers, and place orders.

🧭 Project Structure
abclogistics/
├── src/main/java/com/example/cargoconnect/
│   ├── controller/         # All controllers (Admin, User)
│   ├── entity/             # JPA entity classes (Driver, Truck, Carrier, Order, etc.)
│   ├── repository/         # Spring Data JPA repositories
│   ├── service/            # Business logic layer
│   └── CargoConnectApplication.java
├── src/main/resources/
│   ├── application.properties
│   ├── static/             # HTML, CSS, JS files
│   └── templates/
├── pom.xml
└── README.md

🚀 Application Flow
Admin logs in and adds drivers, trucks, carriers, and addresses.
User places a cargo order with pickup and delivery details.
Admin assigns a driver and truck to the order.
System updates the order and cargo status dynamically.
Users can view their booking confirmations.

💡 Future Enhancements
Add authentication using Spring Security + JWT
Implement order tracking in real time
Add analytics dashboard for admin insights

🏁 Conclusion
CargoConnect demonstrates end-to-end full-stack development using Java and modern web technologies.
It provides a robust solution for automating logistics workflows — from cargo booking to delivery management.

👨‍💻 Author
 Vemula Niharika
📧 niharikavemula03@gmail.com
