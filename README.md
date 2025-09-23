# 🍔 iHungry Burger - Order Management System

A console-based burger ordering and management system built in Java, demonstrating object-oriented programming principles.

![Version](https://img.shields.io/badge/version-2.2-blue.svg)
![Java](https://img.shields.io/badge/Java-8%2B-orange.svg)
![License](https://img.shields.io/badge/license-Educational-green.svg)
![Last Updated](https://img.shields.io/badge/updated-September%202025-brightgreen.svg)
![Status](https://img.shields.io/badge/Status-Active%20Development-green.svg)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [System Architecture](#system-architecture)
- [Installation & Setup](#installation--setup)
- [Usage Guide](#usage-guide)
- [Code Structure](#code-structure)
- [Technical Details](#technical-details)
- [Development Notes](#development-notes)
- [Contributing](#contributing)

## 🎯 Overview

**iHungry Burger** is a comprehensive order management system designed for a burger restaurant. The system allows staff to manage customer orders, track order statuses, analyze customer data, and maintain order records efficiently. Built with Java, it showcases fundamental OOP concepts including encapsulation, data validation, and modular programming.

## ✨ Features

### 🛒 Order Management
- **Place New Orders**: Create orders with auto-generated order IDs
- **Order Tracking**: Monitor order status (Preparing, Delivered, Cancelled)
- **Order Updates**: Modify order quantities and status
- **Order Search**: Find specific orders by Order ID

### 👥 Customer Management
- **Customer Profiles**: Store customer information with phone number validation
- **Customer Search**: Lookup customer details and order history
- **Best Customer Analysis**: Identify top customers by total spending

### 📊 Reporting & Analytics
- **Order Reports**: View orders by status (Delivered, Preparing, Cancelled)
- **Customer Analytics**: Track customer spending and order patterns
- **Order History**: Complete order tracking and management

### 🔒 Data Validation
- **Phone Number Validation**: 10-digit phone numbers starting with 0
- **Order ID Validation**: Format validation (B0001, B0002, etc.)
- **Input Sanitization**: Comprehensive error handling and validation

## 🏗️ System Architecture

### Class Structure

```
iHungry System
├── Burger (Entity Class)
│   ├── Order Properties (ID, Customer, Quantity, Status)
│   ├── Constants (Price, Status Codes)
│   └── Getters/Setters
│
└── IHungry (Main Application Class)
    ├── Order Management Methods
    ├── Customer Management Methods
    ├── Search & Analytics Methods
    ├── Validation Utilities
    └── UI Management Methods
```

### Data Model

**Order Entity:**
- Order ID (Format: B0001-B9999)
- Customer ID (10-digit phone number)
- Customer Name
- Order Quantity
- Order Status (0=Cancelled, 1=Preparing, 2=Delivered)

## 🚀 Installation & Setup

### Prerequisites
- **Java JDK 8 or higher**
- **Command Line Interface** (Terminal/PowerShell/Command Prompt)

### Local Setup

1. **Navigate to project directory:**
   ```bash
   cd "C:\Users\pasin\Desktop\iCET\OOP-iHungry"
   ```

2. **Compile the Java program:**
   ```bash
   javac IHungry.java
   ```

3. **Run the application:**
   ```bash
   java IHungry
   ```

### Alternative Setup
```bash
# If you have the files elsewhere:
# 1. Ensure IHungry.java is in your working directory
# 2. Compile: javac IHungry.java
# 3. Run: java IHungry
```

## 📖 Usage Guide

### Main Menu Options

When you start the application, you'll see the main menu:

```
--------------------------------------------------------------
|                       iHungry Burger                       |
--------------------------------------------------------------

[1] Place Order          [2] Search Best Customer
[3] Search Order         [4] Search Customer
[5] View Orders          [6] Update Order Details
[7] Exit
```

### 1. 📝 Place Order
- Enter customer phone number (10 digits, starting with 0)
- If new customer, enter name; existing customers are auto-populated
- Specify burger quantity
- Confirm order to add to system
- Order receives auto-generated ID (B0001, B0002, etc.)

### 2. 🏆 Search Best Customer
- View customers ranked by total spending
- Shows Customer ID, Name, and Total Amount
- Helps identify most valuable customers

### 3. 🔍 Search Order
- Enter Order ID to find specific order
- Displays complete order details:
  - Order ID, Customer ID, Name
  - Quantity, Order Value, Status

### 4. 👤 Search Customer
- Enter customer phone number
- View customer profile and complete order history
- Shows all orders with quantities and values

### 5. 📋 View Orders
Choose from order status categories:
- **Delivered Orders**: Completed orders
- **Preparing Orders**: Orders in progress
- **Cancelled Orders**: Cancelled orders

### 6. ✏️ Update Order Details
- Search order by ID
- Update quantity or status
- Cannot modify delivered/cancelled orders
- Real-time value calculation

## 🗂️ Code Structure

### Core Files

| File | Description |
|------|-------------|
| `IHungry.java` | Main application with all classes and logic |
| `Burger.class` | Compiled Burger entity class |
| `IHungry.class` | Compiled main application class |
| `.gitignore` | Git ignore rules (excludes .class files) |
| `README.md` | This documentation file |

### Key Classes

#### 🍔 Burger Class
```java
class Burger {
    // Constants
    public static final double BURGER_PRICE = 500.0;
    public static final int CANCEL = 0;
    public static final int PREPARING = 1;
    public static final int DELIVERED = 2;
    
    // Properties
    private String orderId;
    private String customerId;
    private String customerName;
    private int orderQty;
    private int orderStatus;
    
    // Constructors, getters, and setters
}
```

#### 🏪 IHungry Class
Main application class containing:
- **Order Management**: Place, search, update orders
- **Customer Management**: Customer operations and analytics
- **Data Validation**: Input validation methods
- **Search Operations**: Optimized search algorithms for customers and orders
- **UI Methods**: Console interface management
- **Utility Methods**: Array operations, dynamic resizing, and formatting

### Key Methods

| Method | Purpose |
|--------|---------|
| `placeOrder()` | Handle new order creation |
| `searchBestCustomer()` | Analyze and rank customers by spending |
| `searchOrder()` | Find orders by ID |
| `searchCustomer()` | Customer lookup and history |
| `viewOrders()` | Display orders by status |
| `updateOrderDetails()` | Modify existing orders |
| `isValidCustomerId()` | Validate phone numbers |
| `isValidOrderId()` | Validate order ID format |
| `search()` | Generic search method for customer arrays |
| `extendBurgerArray()` | Dynamically resize order array |

## ⚙️ Technical Details

### Data Storage
- **In-Memory Storage**: Uses dynamic arrays (`Burger[]`)
- **Initial Array Size**: Empty array initialization (`new Burger[]{}`) for optimal memory usage
- **Array Management**: Dynamic resizing with `extendBurgerArray()`
- **Memory Optimization**: Starts with zero allocation, grows as needed
- **No Database**: All data lost on application exit

### Validation Rules

#### Customer ID (Phone Number)
- Must be exactly 10 digits
- Must start with '0'
- Only numeric characters allowed
- Example: `0712345678`

#### Order ID
- Format: `B` + 4 digits
- Auto-generated sequentially
- Case-insensitive validation
- Examples: `B0001`, `B0023`, `B1234`

### Order Status Codes
- `0` - Cancelled
- `1` - Preparing (default for new orders)
- `2` - Delivered

### Pricing
- **Fixed Price**: LKR 500.00 per burger
- **Dynamic Calculation**: Total = Quantity × Price
- **Real-time Updates**: Automatic recalculation on quantity changes

## 🔧 System Requirements

### Minimum Requirements
- **Java Version**: JDK 8+
- **Memory**: 64MB RAM
- **Storage**: 10MB free space
- **OS**: Windows, macOS, or Linux

### Recommended Environment
- **Java Version**: JDK 11 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code
- **Terminal**: Command line access for execution

## 🧪 Testing the System

### Sample Test Cases

1. **Valid Order Creation:**
   ```
   Customer ID: 0712345678
   Customer Name: John Doe
   Quantity: 3
   Expected: Order created with ID B0001, Total: 1500.00
   ```

2. **Invalid Phone Number:**
   ```
   Input: 12345 (too short)
   Expected: Error message and retry prompt
   ```

3. **Order Status Update:**
   ```
   Order ID: B0001
   New Status: 2 (Delivered)
   Expected: Status updated successfully
   ```

## 💻 Development Notes

### Current Status
- **Development Phase**: Active local development
- **Repository Status**: Local project (no remote origin configured)
- **Version Control**: Git initialized locally
- **Environment**: Windows development machine

### Recent Updates
- **Memory Optimization**: Improved array initialization for better memory usage
- Enhanced array handling and data structures
- Optimized customer ranking algorithm
- Improved search functionality
- Consolidated data management approaches
- Zero initial memory allocation approach

## 🚧 Known Limitations

1. **No Data Persistence**: Data is lost when application closes
2. **Single Session**: No multi-user support
3. **Fixed Pricing**: No dynamic pricing or discounts
4. **Limited Validation**: Basic input validation only
5. **Console Only**: No graphical user interface

## 🔮 Future Enhancements

### Performance Optimizations (v2.2)
- **Memory Efficiency**: Zero initial allocation reduces memory footprint
- **Dynamic Growth**: Array grows only when orders are placed
- **Resource Management**: Better handling of system resources
- **Scalability**: Improved performance for varying workloads

### Potential Future Improvements
- **Database Integration**: MySQL/PostgreSQL for data persistence
- **GUI Development**: JavaFX or Swing interface
- **Multi-item Menu**: Support for different food items
- **Payment Integration**: Payment processing capabilities
- **User Authentication**: Login system for staff
- **Reporting System**: Advanced analytics and reports
- **Web Interface**: Online ordering capability

### Suggested Features
- Order modification time limits
- Customer loyalty program
- Inventory management
- Staff management system
- Receipt generation

## 📝 Changelog

### Version 2.2 (Current)
- **⚡ Memory Optimization**: Changed array initialization from fixed size to empty array (`new Burger[]{}`)
- **🔧 Performance Enhancement**: Improved memory efficiency with zero initial allocation
- **📈 Scalability**: Better resource management for varying order volumes
- **🏠 Local Development**: Continued local-only development approach

### Version 2.1
- **🏠 Local Development**: Transitioned to local-only development
- **🔄 Code Refinements**: Continued optimization of core methods
- **📚 Documentation**: Updated README for current project state
- **⚙️ Configuration**: Removed remote repository dependencies

### Version 2.0
- **🔄 Refactored** `searchBestCustomer()` method for better performance
- **✨ Enhanced** array handling with improved data structures
- **🗑️ Removed** redundant `searchId()` and `searchName()` methods
- **⚡ Optimized** customer ranking algorithm
- **🔧 Fixed** initial array sizing issue
- **📦 Consolidated** customer data management

### Version 1.0
- **🎉 Initial** release with basic order management
- **📱 Core** features: Place order, search, update, view orders
- **👤 Customer** management and analytics
- **🔍 Order** tracking and status management

## 📄 License

This project is developed for educational purposes as part of Object-Oriented Programming coursework.

## 👨‍💻 Author

**PasinduOG**
- Local Development Project
- Object-Oriented Programming Demonstration

## 🤝 Contributing

This is primarily an educational project for learning OOP concepts. If you'd like to contribute:

1. **Local Development**: Fork or download the project
2. **Make Changes**: Enhance features or fix bugs
3. **Test Thoroughly**: Ensure all functionality works
4. **Share**: Contribute back through appropriate channels

### Development Guidelines
- Follow Java coding conventions
- Add comments for complex logic
- Test changes thoroughly
- Maintain backward compatibility

## 📞 Support

For questions about this project or Java programming concepts demonstrated here, feel free to reach out through educational channels or programming communities.

---

*Built with ❤️ for learning Object-Oriented Programming concepts*

**Last Updated**: September 23, 2025