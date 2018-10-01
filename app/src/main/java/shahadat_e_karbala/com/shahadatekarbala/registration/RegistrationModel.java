package shahadat_e_karbala.com.shahadatekarbala.registration;

public class RegistrationModel {

    private String regId;
    private String regName;
    private String regFatherName;
    private String regMotherName;
    private String regRelation;
    private String regBirth;
    private String regBloodGroup;
    private String regMobileNumber;
    private String regNid;
    private String regEmail;
    private String regPresentAddress;
    private String regPermanentAddress;
    private String regPhotoName;
    private String regPhotoPath;
    private String createdById;
    private String updatedById;
    private String createdAt;

    public RegistrationModel(String regId, String regName, String regFatherName, String regMotherName, String regRelation, String regBirth, String regBloodGroup, String regMobileNumber, String regNid, String regEmail, String regPresentAddress, String regPermanentAddress, String regPhotoName, String regPhotoPath, String createdById, String updatedById, String createdAt) {
        this.regId = regId;
        this.regName = regName;
        this.regFatherName = regFatherName;
        this.regMotherName = regMotherName;
        this.regRelation = regRelation;
        this.regBirth = regBirth;
        this.regBloodGroup = regBloodGroup;
        this.regMobileNumber = regMobileNumber;
        this.regNid = regNid;
        this.regEmail = regEmail;
        this.regPresentAddress = regPresentAddress;
        this.regPermanentAddress = regPermanentAddress;
        this.regPhotoName = regPhotoName;
        this.regPhotoPath = regPhotoPath;
        this.createdById = createdById;
        this.updatedById = updatedById;
        this.createdAt = createdAt;
    }

    public RegistrationModel(String regName, String regFatherName, String regMotherName, String regRelation, String regBirth, String regBloodGroup, String regMobileNumber, String regNid, String regEmail, String regPresentAddress, String regPermanentAddress, String regPhotoName, String regPhotoPath) {
        this.regName = regName;
        this.regFatherName = regFatherName;
        this.regMotherName = regMotherName;
        this.regRelation = regRelation;
        this.regBirth = regBirth;
        this.regBloodGroup = regBloodGroup;
        this.regMobileNumber = regMobileNumber;
        this.regNid = regNid;
        this.regEmail = regEmail;
        this.regPresentAddress = regPresentAddress;
        this.regPermanentAddress = regPermanentAddress;
        this.regPhotoName = regPhotoName;
        this.regPhotoPath = regPhotoPath;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegPhotoName() {
        return regPhotoName;
    }

    public void setRegPhotoName(String regPhotoName) {
        this.regPhotoName = regPhotoName;
    }

    public String getRegPhotoPath() {
        return regPhotoPath;
    }

    public void setRegPhotoPath(String regPhotoPath) {
        this.regPhotoPath = regPhotoPath;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegFatherName() {
        return regFatherName;
    }

    public void setRegFatherName(String regFatherName) {
        this.regFatherName = regFatherName;
    }

    public String getRegMotherName() {
        return regMotherName;
    }

    public void setRegMotherName(String regMotherName) {
        this.regMotherName = regMotherName;
    }

    public String getRegRelation() {
        return regRelation;
    }

    public void setRegRelation(String regRelation) {
        this.regRelation = regRelation;
    }

    public String getRegBirth() {
        return regBirth;
    }

    public void setRegBirth(String regBirth) {
        this.regBirth = regBirth;
    }

    public String getRegBloodGroup() {
        return regBloodGroup;
    }

    public void setRegBloodGroup(String regBloodGroup) {
        this.regBloodGroup = regBloodGroup;
    }

    public String getRegMobileNumber() {
        return regMobileNumber;
    }

    public void setRegMobileNumber(String regMobileNumber) {
        this.regMobileNumber = regMobileNumber;
    }

    public String getRegNid() {
        return regNid;
    }

    public void setRegNid(String regNid) {
        this.regNid = regNid;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public String getRegPresentAddress() {
        return regPresentAddress;
    }

    public void setRegPresentAddress(String regPresentAddress) {
        this.regPresentAddress = regPresentAddress;
    }

    public String getRegPermanentAddress() {
        return regPermanentAddress;
    }

    public void setRegPermanentAddress(String regPermanentAddress) {
        this.regPermanentAddress = regPermanentAddress;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
