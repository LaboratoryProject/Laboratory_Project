import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestAnalyseFormComponent } from './test-analyse-form.component';

describe('TestAnalyseFormComponent', () => {
  let component: TestAnalyseFormComponent;
  let fixture: ComponentFixture<TestAnalyseFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TestAnalyseFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestAnalyseFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
