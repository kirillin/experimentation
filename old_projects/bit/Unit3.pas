unit Unit3;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls, Vcl.ExtCtrls, Math;

type
  TForm3 = class(TForm)
    Edit1: TEdit;
    Edit2: TEdit;
    Label1: TLabel;
    Label2: TLabel;
    Button1: TButton;
    Button2: TButton;
    Timer1: TTimer;
    procedure Button2Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    procedure Edit2Click(Sender: TObject);
    procedure Edit2KeyPress(Sender: TObject; var Key: Char);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form3: TForm3;
  speed, time, trueTotalSymbols: longint;
  smpPhrase: string;

implementation

{$R *.dfm}

uses Unit1;
var
  Form1: TForm1;

procedure TForm3.Button1Click(Sender: TObject);
var
  login, phrase: string;
begin
  phrase := Form3.Edit2.Text;
end;

procedure TForm3.Button2Click(Sender: TObject);
begin
  Form1.Show;
  //Form3.Close;
end;

procedure TForm3.Edit2Click(Sender: TObject);
var
  login: string;
  smpTime, smpSpeed, smpAvgTotalTime, smpAvgSpeed: longint;
begin
  login := Form3.Edit1.Text;
  if (FileExists('param.' + login)) then
  begin
    AssignFile(input, 'param.' + login); reset(input);
    readln(smpPhrase);
    readln(smpTime);
    readln(smpSpeed);
    readln(smpAvgTotalTime);
    readln(smpAvgSpeed);
    CloseFile(input);
    Form3.Timer1.Enabled := true;
  end else
  begin
    ShowMessage('Такого пользователя не существует...');
  end;
end;

procedure TForm3.Edit2KeyPress(Sender: TObject; var Key: Char);
begin
  currentTick := GetTickCount;
  if (firstTick = 0) then
  begin
    firstTick := GetTickCount;
  end;
end;

procedure TForm3.Timer1Timer(Sender: TObject);
var
  wpm: longint;
  diff: extended;
  posLastSymb: longint;
begin
  currentText := Form3.Edit2.Text;
  totalSymbols := length(currentText);
  if totalSymbols < 1 then
    firstTick := 0;
    if totalSymbols = trueTotalSymbols then
      Form3.Timer1.Enabled := false;
  if ((firstTick > 0) and (firstTick <> currentTick)) then
  begin
    diff := currentTick - firstTick;
    wpm := ceil(totalSymbols / (diff / 1000 / 60));
  end else
  begin
    diff := 0;
    wpm := 1;
  end;
  speed := wpm;
  time := ceil(diff);
end;


end.
