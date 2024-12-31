import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestAnalyseListeComponent } from './test-analyse-liste.component';

describe('TestAnalyseListeComponent', () => {
  let component: TestAnalyseListeComponent;
  let fixture: ComponentFixture<TestAnalyseListeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TestAnalyseListeComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TestAnalyseListeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
