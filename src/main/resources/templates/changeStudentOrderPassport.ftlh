<#import "common/common.ftlh" as com>
<@com.main>
    <h2>${facultyName}</h2>
    <div>Change Passport Information</div>
    <form method="post" enctype="multipart/form-data">
        <div><label>Passport Seria :
                <input type="text" name="passportSeria" value="${passportInfo.getPassportSeria()}">
            </label></div>
        <div><label>Passport Number :
                <input type="text" name="passportNumber" value="${passportInfo.getPassportNumber()}">
            </label></div>
        <div><label>Registration Office Name :
                <input type="text" name="registrationOffice" value="${passportInfo.getRegistrationOfficeName()}">
            </label></div>
        <div><label>Issue Date :
                <input type="date" name="issueDate" value="${passportInfo.getIssueDate()}">
            </label></div>
        <div>
            <p>Passport Photo</p>
            <input type="file" name="passportPhotofile" id="passport">
        </div>
        <button type="submit">Save</button>
    </form>
    <div>
        <img src="/img/${passportInfo.getPassportFilename()}">
    </div>
</@com.main>