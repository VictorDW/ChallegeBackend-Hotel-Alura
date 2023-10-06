
<div align="center">
   <h1>Challenge ONE | Java | Back End | Hotel Alura</h1>
</div>

<p align="center">
  <img src="https://img.shields.io/badge/Alura_ONE-Challenge%233-orange">
  <img src="https://img.shields.io/badge/Status-finalizado-blue"><br>
  <img src="https://img.shields.io/badge/Java-11-red">
  <img src="https://img.shields.io/badge/Versión-1.1-green">
</p>

<p align="center" >
  <img width="300" heigth="300" src="https://user-images.githubusercontent.com/91544872/189419040-c093db78-c970-4960-8aca-ffcc11f7ffaf.png">
</p>

👨🏻‍💻 <strong>Victor A. Agudelo</strong></br>

<a href="https://www.linkedin.com/in/victoragudelodsw/" target="_blank">
<img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white" target="_blank"></a>

## 🛠️Tools: 

- IntelliJ IDEA
- PhpMyadmin


## 🖥️ Tecnologías Utilizadas:

- Java 11
- Biblioteca JCalendar
- MySQL
- JPA Hibernate
- Biblioteca Password4j (Bcrypt)
- Lombok

## :pushpin: Arquitectura

- Modelo-Vista-Controlador (MVC)
- Patrón de diseño Data Transfer Object (DTO)
- Patrón de diseño Data Access Object (DAO)

## 📜 Descripción
<p>El Sistema de Gestión de Reservas de Hospedaje es una aplicación desktop diseñada para simplificar y automatizar la gestión de reservas en un establecimiento de hospedaje, como un hotel o una posada. Esta herramienta proporciona una solución integral para administrar todas las actividades relacionadas con las reservas de habitaciones y huéspedes, optimizando la eficiencia operativa y mejorando la experiencia del cliente.</p>

## 📖 Funcionalidades de la Aplicación :

- <h4>Sistema de Autenticación:</h4> Solo el Usuario Administrador tiene los permisos para acceder a la aplicación.
- <h4>Creación de Reservas:</h4> Permite la creación de reservas, verificando las fechas de entrada y salida ingresadas.
- <h4>Cálculo de Costo:</h4> Calcula automáticamente el costo de la reserva en pesos colombianos (COP) en función de las fechas 
  registradas.
- <h4>Asociación de Huéspedes:</h4> Facilita la asociación de huéspedes existentes a una reserva, verificando su existencia a 
  través de su número de cédula. En caso contrario, registra un nuevo huésped. 
- <h4>Consulta de Reservas:</h4> Permite consultar las reservas registradas hasta el momento utilizando filtros como las fechas de 
  entrada y salida, así como el código de reserva.
- <h4>Consulta de Huéspedes:</h4> Permite consultar los datos de huéspedes registrados a través de su número de cédula.
- <h4>Actualización de Reservas:</h4> Posibilita la actualización de datos de reserva, como las fechas de entrada y salida. Además, se verifica que la fecha de entrada no sea anterior a la fecha de registro y que la fecha de salida no sea anterior a la fecha de entrada. También permite cambiar el método de pago.
- <h4>Actualización de Datos de Huéspedes:</h4> Permite la actualización completa de los datos de huéspedes, con validación de edad basada en la fecha de nacimiento para asegurarse de que sean mayores de edad
- <h4>Eliminación (Soft Delete) de la Reserva y los Huéspedes:</h4> Esta función habilita la realización de un "soft delete" para las reservas y los huéspedes que se encuentran en la base de datos. Esta acción permite conservar un historial exhaustivo de todas las reservas y huéspedes en la aplicación.

## 🗳️ Diagrama de la Base de Datos

![Diseño de la BD](https://github.com/VictorDW/ChallegeBackend-Hotel-Alura/assets/15878117/f11c020e-d4db-4dd3-9eb8-1e5ff308105d)

## Inicio
<p>Esta vista proporciona una introducción inicial a la aplicación. Al hacer clic en "Login" se activarán una serie de procesos que permitirán la creación de un usuario y el registro de las nacionalidades, siempre y cuando sea la primera vez que se ejecute la aplicación. Posteriormente, se mostrará la pantalla de inicio de sesión, que permitirá la autenticación y autorización del usuario administrador para acceder a la aplicación</p>

![inicio-app](https://github.com/VictorDW/ChallegeBackend-Hotel-Alura/assets/15878117/523e61fb-f64d-41c6-abbd-d9b06db4280a)

## Registro de Reserva
<p>Esta vista permite registrar los datos esenciales para una reserva, incluyendo la fecha de entrada, la fecha de salida, el costo de la reserva y el método de pago. Se realiza una validación para asegurarse de que la fecha de entrada sea posterior a la fecha actual y que las fechas de entrada y salida estén en el orden correcto, lo que permite calcular automáticamente el costo de la reserva de manera precisa.</p>

![reserva-app](https://github.com/VictorDW/ChallegeBackend-Hotel-Alura/assets/15878117/0619e727-841d-47b9-8077-7827cf9b10d3)

## Registro del huesped
<p>Esta interfaz proporciona la capacidad de buscar un huésped por su número de cédula en la base de datos. Si el huésped ya existe, se recupera automáticamente; en caso contrario, permite el registro de un nuevo huésped, asociándolo directamente a la reserva.</p>

<p>Antes de registrar un nuevo huésped, se realiza una validación que verifica la fecha de nacimiento del huésped para asegurarse de que sea mayor de edad. Además, se garantiza que todos los campos necesarios estén completos antes de proceder con el registro.</p>

![reserva-huesped-app](https://github.com/VictorDW/ChallegeBackend-Hotel-Alura/assets/15878117/f6a25e95-5065-428f-b12a-76fbff388535)

## Consulta de Reservas y Huespedes

<p>Esta interfaz brinda la capacidad de realizar consultas tanto para reservas como para huéspedes registrados en la aplicación. En el caso de las reservas, es posible aplicar filtros por un rango de fechas y por el código de reserva, lo que facilita la búsqueda de información específica. Para los huéspedes, se permite la consulta a través de su número de cédula, agilizando la recuperación de datos individuales.</p>

![consultar-app](https://github.com/VictorDW/ChallegeBackend-Hotel-Alura/assets/15878117/1c5f1bfe-b1b3-434f-8c59-23cc0c61bfab)

## Actualización de Reservas 

<p>En esta interfaz, el administrador puede obtener y actualizar información crucial, como las fechas de entrada y salida, así como el método de pago asociado a una reserva. Sin embargo, es importante tener en cuenta ciertas condiciones antes de realizar cualquier modificación. Por ejemplo, se verifica que la fecha de entrada no sea anterior a la fecha de registro original, y que la fecha de salida no sea anterior a la fecha de entrada. Esto garantiza la coherencia y la validez de los cambios realizados en las reservas.</p>

![update_reserva-app](https://github.com/VictorDW/ChallegeBackend-Hotel-Alura/assets/15878117/5ad192df-2fe9-4cdb-893b-722c6c400a5f)

## Actualización del Huesped

<p>En esta interfaz, el administrador tiene la capacidad de obtener y actualizar información crítica del huésped, incluyendo su fecha de nacimiento, número de cédula y otros datos relevantes. No obstante, se lleva a cabo una validación exhaustiva para asegurarse de que la fecha de nacimiento del huésped resulte en una edad mayor de edad y que ningún campo quede vacío antes de permitir cualquier modificación. Esto garantiza la integridad de los datos del huésped y cumple con los requisitos necesarios.</p>

![update_huesped-app](https://github.com/VictorDW/ChallegeBackend-Hotel-Alura/assets/15878117/5a206816-786f-4c34-b9c6-6e02a7434f6f)

## Eliminación de Reserva y Huesped

<p>Esta interfaz ofrece la capacidad de realizar un "soft delete," lo que significa que se cambia el estado de la reserva y del huésped sin eliminar los datos reales de la base de datos. Esto permite mantener un historial completo de las reservas y huéspedes en la aplicación, lo que puede ser valioso para el seguimiento y el registro de actividades anteriores sin perder información crítica.</p>

![delete-app](https://github.com/VictorDW/ChallegeBackend-Hotel-Alura/assets/15878117/cc5ec202-0a49-46f7-a2ba-13032d40862b)
