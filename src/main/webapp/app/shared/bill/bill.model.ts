export class Bill {
    public id?: any;
    public name?: string;
    public activated?: Boolean;
    public createdBy?: string;
    public createdDate?: Date;
    public lastModifiedBy?: string;
    public lastModifiedDate?: Date;

    constructor(
        id?: any,
        name?: string,
        activated?: Boolean,
        createdBy?: string,
        createdDate?: Date,
        lastModifiedBy?: string,
        lastModifiedDate?: Date,
    ) {
        this.id = id ? id : null;
        this.activated = activated ? activated : false;
        this.createdBy = createdBy ? createdBy : null;
        this.createdDate = createdDate ? createdDate : null;
        this.lastModifiedBy = lastModifiedBy ? lastModifiedBy : null;
        this.lastModifiedDate = lastModifiedDate ? lastModifiedDate : null;
    }
}
