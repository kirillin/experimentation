unit Unit3;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.Menus, Math, Unit1, Unit2,
  Vcl.ExtCtrls, Vcl.StdCtrls;

type
  TForm3 = class(TForm)
    Edit1: TEdit;
    Edit2: TEdit;
    Label1: TLabel;
    Label2: TLabel;
    Button1: TButton;
    Button2: TButton;
    Timer1: TTimer;
    Label3: TLabel;
    Label4: TLabel;
    procedure Button2Click(Sender: TObject);
    procedure Button1Click(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    procedure Edit2KeyPress(Sender: TObject; var Key: Char);
    procedure FormCreate(Sender: TObject);
    procedure Edit2Enter(Sender: TObject);
    procedure Edit2Click(Sender: TObject);
    procedure Edit1Change(Sender: TObject);
    procedure FormShow(Sender: TObject);

  private
    { Private declarations }
  public
    { Public declarations }
  end;

const
  DIVIATE_TIME = 1000;
  DIVIATE_SPEED = 60;

var
  Form3: TForm3;
  speed, time, trueTotalSymbols: longint;
  smpPhrase, login: string;
  smpTime, smpSpeed, smpAvgTotalTime, smpAvgSpeed: longint;

implementation

{$R *.dfm}


procedure TForm3.Button1Click(Sender: TObject);
var
  login, phrase: string;
begin
  phrase := Form3.Edit2.Text;
  phrase := phrase.Trim;
  smpPhrase := smpPhrase.Trim;
  {
  ShowMessage(phrase + '+++' + smpPhrase );
  ShowMessage(IntToStr(smpAvgTotalTime) + '+++' + IntToStr(time) );
  ShowMessage(IntToStr(smpAvgSpeed) + '+++' + IntToStr(speed) );
  }
  if (phrase = smpPhrase) then
    if (smpAvgTotalTime + DIVIATE_TIME >= time) and
        (smpAvgTotalTime - DIVIATE_TIME <= time) then
      if (smpAvgSpeed + DIVIATE_SPEED >= speed) and
          (smpAvgSpeed - DIVIATE_SPEED <= speed) then
      begin
        Form2.Show;
        Form3.Visible := false;
      end else ShowMessage('Личность не установлена! Повторите попытку ввода идентификационной фразы!');
end;

procedure TForm3.Button2Click(Sender: TObject);
begin
  Form1.Show;
  Form3.Visible := false;
end;

procedure TForm3.Edit1Change(Sender: TObject);
begin
  smpPhrase := '';
  firstTick := 0;
end;

procedure TForm3.Edit2Click(Sender: TObject);
begin
  Form3.Edit2.Clear;
  firstTick := 0;
end;

procedure TForm3.Edit2Enter(Sender: TObject);
var
  f: string;
begin
  login := Form3.Edit1.Text;
  f :=    'params.' + login.Trim;
  if (FileExists(f)) then
  begin
    if (smpPhrase.IsEmpty) then
    begin
      AssignFile(input, f); reset(input);
      readln(input, smpPhrase);
  //    readln(smpTime);
  //    readln(smpSpeed);
      readln(input, smpAvgTotalTime);
      readln(input, smpAvgSpeed);
      CloseFile(input);
      //ShowMessage(smpPhrase);
      trueTotalSymbols := length(smpPhrase);
      Form3.Timer1.Enabled := true;
    end;
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
    Form3.Timer1.Enabled := true;
    firstTick := GetTickCount;
  end;
end;

procedure TForm3.FormCreate(Sender: TObject);
begin
  Form3.Edit1.Clear;
  Form3.Edit2.Clear;
  smpPhrase := '';
end;

procedure TForm3.FormShow(Sender: TObject);
begin
  Form3.Edit1.Clear;
  Form3.Edit2.Clear;
  smpPhrase := '';
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
  Form3.Label3.Caption := 'Скорость: ' + IntToStr(speed);
  Form3.Label4.Caption := 'Время: ' + IntToStr(time);
end;


end.
