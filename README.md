
Darbuotojų Pamainų Planavimo Sistema
Šis projektas yra paprasta darbuotojų pamainų planavimo sistema, leidžianti administratoriui kurti ir valdyti darbuotojų tvarkaraščius.

🔧 Funkcionalumas
✅ Darbuotojų registracija ir prisijungimas

🗓️ Pamainų sudarymas ir redagavimas

👥 Darbuotojų paskyrimas pamainoms

📊 Vartotojo informacinė suvestinė

🔒 Vartotojų rolės (darbuotojas / administratorius)

💻 Naudojamos technologijos
Frontend: React / HTML / CSS / JavaScript

Backend: Java

Duomenų bazė: MySQL Workbench

Kitos bibliotekos: Mongoose, JWT, bcrypt ir kt.

🚀 Projekto paleidimas lokaliai
Klonuok repozitoriją:

bash
Copy
Edit
git clone https://github.com/Matas313/employee-shift-planner.git
Įdiek priklausomybes frontend ir backend:

bash
Copy
Edit
cd employee-shift-planner
npm install
cd client
npm install
Sukurk .env failą su šiais kintamaisiais:

env
Copy
Edit
MONGO_URI=your_mongodb_connection_string
JWT_SECRET=your_jwt_secret
PORT=5000
Paleisk serverį:

bash
Copy
Edit
cd ..
npm run server
Paleisk frontend:

bash
Copy
Edit
cd client
npm start
Sistema bus pasiekiama per http://localhost:3000