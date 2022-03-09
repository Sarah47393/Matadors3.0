<?php

namespace App\Entity;

use App\Repository\UserRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * @ORM\Entity(repositoryClass=UserRepository::class)
 */
class User
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */ 
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $prenom;

    /**
     * @ORM\Column(type="integer")
     */
     
    private $cin;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $role;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $access;

    /**
     * @ORM\Column(type="date")
     */
    private $datenaissance;
    
    /**
     * @ORM\Column(type="integer")
     */
    private $password;

    /**
     * @ORM\OneToMany(targetEntity=Reservation::class, mappedBy="users", orphanRemoval=true)
     */
    private $Reservation;
    /**
     * @ORM\OneToMany(targetEntity=Dons::class, mappedBy="user", orphanRemoval=true)
     */
    private $dons;

    public function __construct()
    {
        $this->Reservation = new ArrayCollection();
        $this->commande = new ArrayCollection();
        $this->dons = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    public function getCin(): ?int
    {
        return $this->cin;
    }

    public function setCin(int $cin): self
    {
        $this->cin = $cin;

        return $this;
    }

    public function getRole(): ?string
    {
        return $this->role;
    }

    public function setRole(string $role): self
    {
        $this->role = $role;

        return $this;
    }

    public function getAccess(): ?string
    {
        return $this->access;
    }

    public function setAccess(string $access): self
    {
        $this->access = $access;

        return $this;
    }
    public function getdatenaissance(): ?\DateTimeInterface
    {
        return $this->datenaissance;
    }

    public function setdatenaissance(\DateTimeInterface $datenaissance): self
    {
        $this->datenaissance = $datenaissance;

        return $this;
    }

    public function getPassword(): ?int
    {
        return $this->password;
    }
    public function setPassword(int $password): self
    {
        $this->password = $password;

        return $this;
    }

    /**
     * @return Collection|Reservation[]
     */
    public function getReservation(): Collection
    {
        return $this->Reservation;
    }

    public function addReservation(Reservation $reservation): self
    {
        if (!$this->Reservation->contains($reservation)) {
            $this->Reservation[] = $reservation;
            $reservation->setUsers($this);
        }

        return $this;
    }

    public function removeReservation(Reservation $reservation): self
    {
        if ($this->Reservation->removeElement($reservation)) {
            // set the owning side to null (unless already changed)
            if ($reservation->getUsers() === $this) {
                $reservation->setUsers(null);
            }
        }

        return $this;
    }
    /**
     * @return Collection|Dons[]
     */
    public function getDons(): Collection
    {
        return $this->dons;
    }

    public function addDon(Dons $don): self
    {
        if (!$this->dons->contains($don)) {
            $this->dons[] = $don;
            $don->setUser($this);
        }

        return $this;
    }

    public function removeDon(Dons $don): self
    {
        if ($this->dons->removeElement($don)) {
            // set the owning side to null (unless already changed)
            if ($don->getUser() === $this) {
                $don->setUser(null);
            }
        }

        return $this;
    }

       public function __toString() :string {
            return $this->nom;
        }
    }


