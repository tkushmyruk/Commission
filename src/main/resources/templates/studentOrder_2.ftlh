<#import "common/common.ftlh" as com>
<@com.main>
    <h2>${facultyName}</h2>
    <div>Add Personal Information</div>
    <form method="post">
    <div><label>First Name : <input type="text" name="firstName"></label></div>
    <div><label>Middle Name : <input type="text" name="middleName"></label></div>
    <div><label>Last Name : <input type="text" name="lastName"></label></div>
    <div><label>Date Of birth<input type="date" name="dateOfBirth"></label></div>
    <div><label>Nationality : <input type="text" name="nationality"></label></div>
    <div><p>Gender</p>
         <p><input name="gender" type="radio" value="Male">Male</p>
         <p><input name="gender" type="radio" value="Female">Female</p>
    </div>
        <button type="submit">Save</button>
    </form>

</@com.main>