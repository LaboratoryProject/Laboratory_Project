import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OptionsAnalyseComponent } from './options-analyse.component';

describe('OptionsAnalyseComponent', () => {
  let component: OptionsAnalyseComponent;
  let fixture: ComponentFixture<OptionsAnalyseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OptionsAnalyseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OptionsAnalyseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
