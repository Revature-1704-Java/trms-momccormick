import { Injectable } from '@angular/core';
import { Reimbursement } from './Reimbursement';

@Injectable()
export class ReimbursementService {

  constructor() { }
  
  getReimbursements(): Reimbursement[] {    
    return Reimbursements;
  }
  
  getReimbursementById(ReimbursementId: number) {    
    return Reimbursements.find(r => r.id === ReimbursementId);
  }

}

const Reimbursements = [
{},{}
]