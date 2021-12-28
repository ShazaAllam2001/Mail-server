import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FolderToolBarComponent } from './folder-tool-bar.component';

describe('FolderToolBarComponent', () => {
  let component: FolderToolBarComponent;
  let fixture: ComponentFixture<FolderToolBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FolderToolBarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FolderToolBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
