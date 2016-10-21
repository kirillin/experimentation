unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls, Vcl.ExtCtrls, Math, Unit3;

type
  TForm1 = class(TForm)
    Edit1: TEdit;
    Edit2: TEdit;
    Label1: TLabel;
    Label4: TLabel;
    Button1: TButton;
    Timer1: TTimer;
    Label2: TLabel;
    Label3: TLabel;
    Edit3: TEdit;
    Label5: TLabel;
    procedure Edit1KeyPress(Sender: TObject; var Key: Char);
    procedure FormCreate(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    procedure Edit2KeyPress(Sender: TObject; var Key: Char);
    procedure Edit1Click(Sender: TObject);
    procedure Edit2Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;
  Form3: TForm3;
  authPhrase: string;
  time, totalSymbols: longint;
  firstTick, currentTick: TDateTime;
  currentText, firstText: string;
  speed, avgSpeed, totalTime, avgTotalTime: longint;
implementation

{$R *.dfm}

procedure init();
begin
  firstTick := 0;
  currentTick := 0;
  totalSymbols := 0;
  currentText := '';
  firstText := '';
  totalTime := 0;
  speed := 0;
  avgTotalTime := 0;
  avgSpeed := 0;
  Form1.Timer1.Enabled := false;
end;

procedure TForm1.Button1Click(Sender: TObject);
var
  f: TextFile;
  login: string;
begin
  login := Form1.Edit3.Text;
  AssignFile(f, 'params.' + login); rewrite(f);
  writeln(f, firstText);
  writeln(f, totalTime);
  writeln(f, speed);
  writeln(f, avgTotalTime);
  Writeln(f, avgSpeed);
  CloseFile(f);
  Form1.Close;
  Form3.Show;
end;

procedure TForm1.Edit1Click(Sender: TObject);
begin
   Form1.Timer1.Enabled := true;
   Form1.Edit2.Clear;
end;

procedure TForm1.Edit1KeyPress(Sender: TObject; var Key: Char);
begin
  currentTick := GetTickCount;
  if (firstTick = 0) then
  begin
    Form1.Timer1.Enabled := true;
    firstTick := GetTickCount;
  end;
end;

procedure TForm1.Edit2Click(Sender: TObject);
begin
  firstTick := 0;
  currentTick := 0;
end;

procedure TForm1.Edit2KeyPress(Sender: TObject; var Key: Char);
begin
  currentTick := GetTickCount;
  if (firstTick = 0) then
  begin
    firstTick := GetTickCount;
  end;
end;

procedure TForm1.FormCreate(Sender: TObject);
begin
  Form1.Edit1.Clear;
  Form1.Edit2.Clear;
  Form1.Edit3.Clear;
  init();
end;

procedure TForm1.Timer1Timer(Sender: TObject);
var
  wpm: longint;
  diff: extended;
  posLastSymb: longint;
  firstPhrase, secondPhrase: string;
begin
  currentText := Form1.Edit1.Text;
  totalSymbols := length(currentText);
  if totalSymbols < 1 then
    firstTick := 0;
  if ((firstTick > 0) and (firstTick <> currentTick)) then
  begin
    diff := currentTick - firstTick;
    wpm := ceil(totalSymbols / (diff / 1000 / 60));
    posLastSymb := length(Form1.Edit2.Text);
    if (posLastSymb <> 0) then
    begin
      secondPhrase := Form1.Edit2.Text;
      firstPhrase := copy(currentText, 1, posLastSymb);
      if (firstPhrase <> secondPhrase) then
        Form1.Edit2.Font.Color := clRed
      else
      Form1.Edit2.Font.Color := clBlack;
      if (posLastSymb = length(currentText)) then
        Form1.Timer1.Enabled := false;
    end;
  end else
  begin
    diff := 0;
    wpm := 1;
  end;
  if (Form1.Edit1.Focused) then
  begin
    totalTime := ceil(diff);
    speed := wpm;
    Form1.Label3.Caption := 'Время ввода фразы: ' + IntToStr(totalTime);
    Form1.Label4.Caption := 'Скорость набора: ' + IntToStr(speed);
    firstText := currentText;
  end else if (Form1.Edit2.Focused) then
  begin
    avgTotalTime := (totalTime + ceil(diff)) div 2;
    avgSpeed := (speed + wpm) div 2;
    Form1.Label3.Caption := 'Среднее время ввода фразы: ' + IntToStr(avgTotalTime);
    Form1.Label4.Caption := 'Средняя скорость набора: ' + IntToStr(avgSpeed);
  end;
end;

end.

