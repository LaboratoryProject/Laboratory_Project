import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule } from '@angular/material/dialog';
import { AnalyseDashboardComponent } from './analyse-dashboard.component';

describe('AnalyseDashboardComponent', () => {
  let component: AnalyseDashboardComponent;
  let fixture: ComponentFixture<AnalyseDashboardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AnalyseDashboardComponent],
      imports: [MatDialogModule],
    }).compileComponents();

    fixture = TestBed.createComponent(AnalyseDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should open add analyse dialog on button click', () => {
    spyOn(component, 'openAddAnalyseDialog');
    const button = fixture.debugElement.nativeElement.querySelector('button');
    button.click();
    expect(component.openAddAnalyseDialog).toHaveBeenCalled();
  });
});
