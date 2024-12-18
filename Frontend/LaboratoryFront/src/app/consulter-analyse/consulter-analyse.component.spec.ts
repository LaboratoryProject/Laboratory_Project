import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsulterAnalyseComponent } from './consulter-analyse.component';

describe('ConsulterAnalyseComponent', () => {
  let component: ConsulterAnalyseComponent;
  let fixture: ComponentFixture<ConsulterAnalyseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ConsulterAnalyseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsulterAnalyseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
