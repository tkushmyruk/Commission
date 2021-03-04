<#import "common/common.ftlh" as com>
<@com.main>
    <h2>${facultyName}</h2>
    <div>Add Passport Information</div>
    <form method="post" enctype="multipart/form-data">
    <div><label>Passport Seria : <input type="text" name="passportSeria"></label></div>
    <div><label>Passport Number : <input type="text" name="passportNumber"></label></div>
    <div><label>Registration Office Name : <input type="text" name="registrationOffice"></label></div>
    <div><label>Issue Date : <input type="date" name="issueDate"></label></div>
        <div>
            <p>Passport Photo</p>
            <input type="file" name="passportPhotofile" id="passport">
        </div>
        <button type="submit">Save</button>
    </form>
</@com.main>