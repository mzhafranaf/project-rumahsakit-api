# API Specification

## Authentication

### Register

... _not ready_ ...

### Login

... _not ready_ ...

## Doctor

### Create Doctor

Request :
- Method : POST
- Endpoint : `/rs-kawahedukasi/doctor`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "fullName" : "String",
  "email" : "String",
  "phoneNumber" : "String",
  "specialist" : "String",
  "salary" : "long"
}
```

Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : {
    "id" : "long",
    "fullName" : "String",
    "email" : "String",
    "phoneNumber" : "String",
    "specialist" : "String",
    "salary" : "long",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
}
```

### Read Doctor List

Request :
- Method : GET
- Endpoint : `/rs-kawahedukasi/doctor/list`
- Header :
  - Accept : application/json
- Query Param :
  - page : number

Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : [
    {
      "id" : "long",
      "fullName" : "String",
      "email" : "String",
      "phoneNumber" : "String",
      "specialist" : "String",
      "salary" : "long",
      "createdAt" : "date",
      "updatedAt" : "date"
    },
    {
      "id" : "long",
      "fullName" : "String",
      "email" : "String",
      "phoneNumber" : "String",
      "specialist" : "String",
      "salary" : "long",
      "createdAt" : "date",
      "updatedAt" : "date"
    }
  ]
}
```

## Nurse

### Create Nurse

Request :
- Method : POST
- Endpoint : `/rs-kawahedukasi/nurse`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "fullName" : "String",
  "gender" : "String",
  "email" : "String",
  "phoneNumber" : "String",
  "salary" : "long"
}
```

- Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : {
    "id" : "long",
    "fullName" : "String",
    "gender" : "String",
    "email" : "String",
    "phoneNumber" : "String",
    "salary" : "long",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
}
```

### Read Nurse List

Request :
- Method : GET
- Endpoint : `/rs-kawahedukasi/nurse/list`
- Header :
  - Accept : application/json
- Query Param : 
  - page : number

Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : [
    {
      "id" : "long",
      "fullName" : "String",
      "gender" : "string",
      "email" : "String",
      "phoneNumber" : "String",
      "salary" : "long",
      "createdAt" : "date",
      "updatedAt" : "date"
    },
    {
      "id" : "long",
      "fullName" : "String",
      "gender" : "String",
      "email" : "String",
      "phoneNumber" : "String",
      "salary" : "long",
      "createdAt" : "date",
      "updatedAt" : "date"
    }
  ]
}
```

## Staff

### Create Staff

Request :
- Method : POST
- Endpoint : `/rs-kawahedukasi/staff`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "fullName" : "String",
  "gender" : "String",
  "email" : "String",
  "phoneNumber" : "String",
  "salary" : "long"
}
```

- Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : {
    "id" : "long",
    "fullName" : "String",
    "gender" : "String",
    "email" : "String",
    "phoneNumber" : "String",
    "salary" : "long",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
}
```

### Read Staff List

Request :
- Method : GET
- Endpoint : `/rs-kawahedukasi/staff/list`
- Header :
  - Accept : application/json
- Query param :
  - page : number

Response :
```json
{
  "code" : "number",
  "status" : "String",
  "data" : [
    {
      "id" : "long",
      "fullName" : "String",
      "gender" : "String",
      "email" : "String",
      "phoneNumber" : "String",
      "salary" : "long",
      "createdAt" : "date",
      "updatedAt" : "date"
    },
    {
      "id" : "long",
      "fullName" : "String",
      "gender" : "String",
      "email" : "String",
      "phoneNumber" : "String",
      "salary" : "long",
      "createdAt" : "date",
      "updatedAt" : "date"
    }
  ]
}
```

### Delete Staff

Request :
- Method : DELETE
- Endpoint : `/rs-kawahedukasi/staff/{id_staff}`
- Header : 
  - Accept : application/json

Response :

```json
{
  "code" : "number",
  "status" : "String"
}
```

## Patient

### Create Patient

Request :
- Method : POST
- Endpoint : `/rs-kawahedukasi/patient`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "fullName" : "String",
  "gender" : "String",
  "email" : "String",
  "phoneNumber" : "String",
  "status" : "String",
  "address" : "String",
  "isCoverBPJS" : "String"
}
```

- Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : {
    "id" : "long",
    "fullName" : "String",
    "gender" : "String",
    "email" : "String",
    "phoneNumber" : "String",
    "status" : "String",
    "address" : "String",
    "isCoverBPJS" : "String",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
}
```

### Read Patient List

Request :
- Method : GET
- Endpoint : `/rs-kawahedulasi/patient/list`
- Header :
  - Accept : application/json
- Query Param :
  - page : number

Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : [
    {
      "id" : "long",
      "fullName" : "String",
      "gender" : "String",
      "email" : "String",
      "phoneNumber" : "String",
      "status" : "String",
      "address" : "String",
      "isCoverBPJS" : "String",
      "createdAt" : "date",
      "updatedAt" : "date"
    },
    {
      "fullName" : "String",
      "gender" : "String",
      "email" : "String",
      "phoneNumber" : "String",
      "status" : "String",
      "address" : "String",
      "isCoverBPJS" : "String"
    }
  ]
}
```

### Update Patient

Request : 
- Method : PUT
- Endpoint : `/rs-kawahedukasi/patient/{id_patient}`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "fullName" : "String",
  "gender" : "String",
  "email" : "String",
  "phoneNumber" : "String",
  "status" : "String",
  "address" : "String",
  "isCoverBPJS" : "String"
}
```

Response :

```json
{
  "code" : "number",
  "status" : "String",
  "code" : {
    "id" : "long",
    "fullName" : "String",
    "gender" : "String",
    "email" : "String",
    "phoneNumber" : "String",
    "status" : "String",
    "address" : "String",
    "isCoverBPJS" : "String",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
  
}
```

### Delete Patient

Request :
- Method : DELETE
- Endpoint : `rs-kawahedukasi/patient/{id_patient}`
- Header :
  - Accept : application/json

Response :

```json
{
  "code" : "number",
  "status" : "string"
}
```

## Medicine

### Create Medicine

Request :
- Method : POST
- Endpoint : `/rs-kawahedukasi/medicine`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "medicineName" : "String",
  "production" : "String",
  "category" : "String",
  "description" : "String"
}
```

- Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : {
    "id" : "long",
    "medicineName" : "String",
    "production" : "String",
    "category" : "String",
    "description" : "String",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
}
```

### Read Medicine Filter

Request :
- Method : GET
- Endpoint : `/rs-kawahedulasi/medicine/list`
- Header :
  - Accept : application/json
- Query Param :

Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : [
    {
      "id" : "long",
      "medicineName" : "String",
      "production" : "String",
      "category" : "String",
      "description" : "String",
      "createdAt" : "date",
      "updatedAt" : "date"
    },
    {
      "id" : "long",
      "medicineName" : "String",
      "production" : "String",
      "category" : "String",
      "description" : "String",
      "createdAt" : "date",
      "updatedAt" : "date"
    }
  ]
}
```

### Update Medicine

Request : 
- Method : PUT
- Endpoint : `/rs-kawahedukasi/medicine/{id_medicine}`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "medicineName" : "String",
  "production" : "String",
  "category" : "String",
  "description" : "String"
}
```

Response :

```json
{
  "code" : "number",
  "status" : "String",
  "code" : {
    "id" : "long",
    "medicineName" : "String",
    "production" : "String",
    "category" : "String",
    "description" : "String",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
  
}
```

### Delete Patient

Request :
- Method : DELETE
- Endpoint : `rs-kawahedukasi/medicine/{id_medicine}`
- Header :
  - Accept : application/json

Response :

```json
{
  "code" : "number",
  "status" : "string"
}
```

## Inpatient Room

### Create Inpatient Room

Request :
- Method : POST
- Endpoint : `/rs-kawahedukasi/inpatient-room`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "roomPrefix" : "String",
  "roomNumber" : "String",
  "roomCategory" : "String",
  "isEmpty" : "boolean"
}
```

- Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : {
    "id" : "long",
    "roomPrefix" : "String",
    "roomNumber" : "String",
    "roomCategory" : "String",
    "isEmpty" : "boolean",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
}
```

### Read Inpatient Room List

Request :
- Method : GET
- Endpoint : `/rs-kawahedulasi/impatient-room/list`
- Header :
  - Accept : application/json
- Query Param :

Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : [
    {
      "id" : "long",
      "roomPrefix" : "String",
      "roomNumber" : "String",
      "roomCategory" : "String",
      "isEmpty" : "boolean",
      "createdAt" : "date",
      "updatedAt" : "date"
    },
    {
      "id" : "long",
      "roomPrefix" : "String",
      "roomNumber" : "String",
      "roomCategory" : "String",
      "isEmpty" : "boolean",
      "createdAt" : "date",
      "updatedAt" : "date"
    }
  ]
}
```

### Update Inpatient Room

Request : 
- Method : PUT
- Endpoint : `/rs-kawahedukasi/inpatient-room/{id_room}`
- Header :
  - Content-Type : application/json
  - Accept : application/json
- Body :

```json
{
  "roomPrefix" : "String",
  "roomNumber" : "String",
  "roomCategory" : "String",
  "isEmpty" : "boolean"
}
```

- Response :

```json
{
  "code" : "number",
  "status" : "String",
  "data" : {
    "id" : "long",
    "roomPrefix" : "String",
    "roomNumber" : "String",
    "roomCategory" : "String",
    "isEmpty" : "boolean",
    "createdAt" : "date",
    "updatedAt" : "date"
  }
}
```

### Delete Inpatient Room

Request :
- Method : DELETE
- Endpoint : `rs-kawahedukasi/inpatient-room/{id_room}`
- Header :
  - Accept : application/json

Response :

```json
{
  "code" : "number",
  "status" : "string"
}
```
