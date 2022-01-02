import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactToolBarComponent } from './contact-tool-bar.component';

describe('ContactToolBarComponent', () => {
  let component: ContactToolBarComponent;
  let fixture: ComponentFixture<ContactToolBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContactToolBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ContactToolBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
