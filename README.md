# 📘 UMS

A modular and extensible content management backend built with **Spring Boot**, designed to manage books, authors, publishers, images, categories, and inventory for a digital library or educational platform.

---

## 🚀 Features

- 📚 Full CRUD support for Books, Authors, Publishers, Categories, and Inventory
- 🖼️ Add multiple images per book (cover and regular)
- 🏷️ Assign multiple categories per book
- 🔄 Partial update support for Book entities
- 📦 Inventory tracking (stock levels per book)
- 📤 Clean API responses with unified format
- 🔧 Designed for integration with modern frontend frameworks (e.g., Angular + PrimeNG)

---

## 🧱 Tech Stack

| Layer        | Technology     |
|--------------|----------------|
| Language     | Java 17+       |
| Framework    | Spring Boot    |
| ORM          | Spring Data JPA (Hibernate) |
| Database     | MySQL          |
| Build Tool   | Maven or Gradle |
| Utilities    | Lombok, MapStruct (optional), Postman |

---

## 📂 Modules Overview

### 📗 Book Management
- Fields: `title`, `price`, `isbn`, `description`, `publishedDate`, `publisher`, `authors`, `categories`, `images`
- Relationships:
  - Many-to-One: Book → Publisher
  - Many-to-Many: Book ↔ Authors
  - Many-to-Many: Book ↔ Categories
  - One-to-One: Book → Inventory
  - One-to-Many: Book → BookImages

### 🖼️ Image Handling
- Upload and assign multiple images to books
- Mark cover image
- Retrieve book images via book ID and type (cover/regular)

### 🧑‍💼 Author & Publisher Management
- Simple CRUD operations
- Each book can have multiple authors and one publisher

### 🏷️ Category Management
- Assign multiple categories to a book
- Filter or organize content using tags

### 📦 Inventory
- One-to-One relation with Book
- Track stock quantity

---

## 📤 API Structure

All responses are unified using a standard response wrapper:

```json
{
  "timestamp": "2025-06-13T22:30:00",
  "message": "Success",
  "status": 200,
  "data": {
    "id": 1,
    "title": "Sample Book",
    "authors": ["Author 1", "Author 2"],
    "categories": ["Java", "Spring Boot"]
  }
}




+---------------------------+
|          Book            |
+---------------------------+
| - bookId : Long           |
| - title : String          |
| - isbn : String           |
| - price : BigDecimal      |
| - description : String    |
| - publishedDate : Date    |
+---------------------------+
| * publisher : Publisher   |
| * authors : Set<Author>   |
| * categories : Set<Category> |
| * images : List<BookImage> |
| * inventory : Inventory   |
+---------------------------+

           | 1
           |
           | many
+---------------------------+
|        Publisher          |
+---------------------------+
| - publisherId : Long      |
| - name : String           |
+---------------------------+


          | many           | many
          |                |
+------------------+   +------------------+
|     Author       |   |    Category      |
+------------------+   +------------------+
| - authorId : Long|   | - categoryId : Long |
| - fullname : String| | - name : String     |
+------------------+   +------------------+


          | one              | many
          |                  |
+------------------+     +------------------+
|    Inventory     |     |    BookImage     |
+------------------+     +------------------+
| - inventoryId: Long     | - imageId : Long |
| - stockQuantity: Int    | - url : String   |
                          | - isCover : Bool |
                          +------------------+

